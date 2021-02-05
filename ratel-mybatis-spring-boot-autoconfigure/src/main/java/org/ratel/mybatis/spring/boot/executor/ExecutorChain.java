package org.ratel.mybatis.spring.boot.executor;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMappedStatement;
import org.ratel.mybatis.spring.boot.sql.source.BoundSql;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 执行sql处理链
 *
 * @author stephen
 * @date 2021/2/5 3:25 下午
 */
public class ExecutorChain {

    /**
     * 责任链模式处理sql执行
     *
     * @param mappedStatement
     * @param dataSource
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> List<T> executeQuery(RatelMappedStatement mappedStatement, DataSource dataSource, Object parameter) {
        Executor executor = new CachingExecutor();
        executor.setNext(new SimpleExecutor());
        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(parameter);
        try {
            return executor.query(mappedStatement, dataSource, parameter, boundSql);
        } catch (SQLException e) {
            throw new RuntimeException("execute query error.", e);
        }
    }

}
