package org.ratel.mybatis.spring.boot.executor;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMappedStatement;
import org.ratel.mybatis.spring.boot.sql.source.BoundSql;
import org.ratel.mybatis.spring.boot.sql.source.ParameterMapping;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单执行JDBC
 *
 * @author stephen
 * @date 2021/2/5 3:37 下午
 */
public class SimpleExecutor extends Executor {

    private Map<String, List<Object>> oneLevelCache = new HashMap<>();

    @Override
    public <T> List<T> query(RatelMappedStatement mappedStatement, DataSource dataSource, Object parameter, BoundSql boundSql) throws SQLException {
        List<Object> params = getParams(mappedStatement, parameter, boundSql.getParameterMappings());

        String sql = boundSql.getSql();
        // 查询一级缓存
        String key  = new StringBuilder(sql).append(" (").append(params).append(")").toString();
        System.out.println("执行sql: " + key);
        List<Object> result = oneLevelCache.get(key);
        if (result != null) {
            return (List<T>) result;
        }

        ResultSet resultSet = executeQuery(mappedStatement, dataSource, params, sql);
        // 处理结果集
        if (resultSet == null) {
            return null;
        }
        try {
            result = getResult(mappedStatement.getResultTypeClass(), resultSet);
            oneLevelCache.put(key, result);
            return (List<T>) result;
        } catch (Exception e) {
           throw new SQLException("result mapping failed.");
        }
    }

    private ResultSet executeQuery(RatelMappedStatement mappedStatement, DataSource dataSource, List<Object> params, String sql) throws SQLException {
        Connection connection = dataSource.getConnection();
        // 判断创建哪种Statement
        ResultSet resultSet = null;
        if ("PREPARED".equals(mappedStatement.getStatementType())) {
            // 创建Statement
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < params.size(); i++) {
                prepareStatement.setObject(i + 1, params.get(i));
            }
            // 执行Statement
            resultSet = prepareStatement.executeQuery();
        }
        return resultSet;
    }

    private List<Object> getParams(RatelMappedStatement mappedStatement, Object parameter, List<ParameterMapping> parameterMappings) throws SQLException {
        if (CollectionUtils.isEmpty(parameterMappings)) {
            return new ArrayList<>();
        } else {
            List<Object> params = new ParamHandlerChain().handle(parameter, parameterMappings, mappedStatement.getParameterTypeClass());
            if (params == null) {
                throw new SQLException("not find params");
            }
            return params;
        }
    }


    private List<Object> getResult(Class<?> resultTypeClass, ResultSet resultSet) throws Exception {
        List<Object> results = new ArrayList();
        // 每次取一行数据
        while (resultSet.next()) {
            Constructor constructor = resultTypeClass.getConstructor();
            Object result =  constructor.newInstance();
            // 获取数据库源数据(列名)
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(result, resultSet.getObject(i + 1));
            }
            results.add(result);
        }
        return results;
    }
}
