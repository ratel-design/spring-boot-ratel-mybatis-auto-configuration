package org.ratel.mybatis.spring.boot.session.defaults;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMappedStatement;
import org.ratel.mybatis.spring.boot.autoconfigure.RatelMybatisConfiguration;
import org.ratel.mybatis.spring.boot.executor.ExecutorChain;
import org.ratel.mybatis.spring.boot.session.SqlSession;

import java.util.List;

/**
 * @author stephen
 * @date 2021/2/1 7:12 下午
 */
public class DefaultSqlSession implements SqlSession {

    private final RatelMybatisConfiguration configuration;

    public DefaultSqlSession(RatelMybatisConfiguration configuration) {
        this.configuration = configuration;
    }


    @Override
    public <T> T selectOne(String statementId, Object parameter) {
        List<T> list = selectList(statementId, parameter);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object parameter) {
        RatelMappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        return new ExecutorChain().executeQuery(mappedStatement, configuration.getDataSource(), parameter);
    }

}
