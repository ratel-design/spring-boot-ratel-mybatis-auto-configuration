package org.ratel.mybatis.spring.boot.session.defaults;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMybatisConfiguration;
import org.ratel.mybatis.spring.boot.session.SqlSession;
import org.ratel.mybatis.spring.boot.session.SqlSessionFactory;

/**
 * @author stephen
 * @date 2021/2/1 7:11 下午
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final RatelMybatisConfiguration configuration;

    public DefaultSqlSessionFactory(RatelMybatisConfiguration configuration) {
        this.configuration = configuration;
    }
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
