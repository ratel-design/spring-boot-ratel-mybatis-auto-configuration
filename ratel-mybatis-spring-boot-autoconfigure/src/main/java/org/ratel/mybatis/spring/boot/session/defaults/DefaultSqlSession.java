package org.ratel.mybatis.spring.boot.session.defaults;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMybatisConfiguration;
import org.ratel.mybatis.spring.boot.session.SqlSession;

import java.io.IOException;

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
    public <T> T selectOne(String statement) {
        // TODO:MQH 2021/2/1
        return null;
    }

    @Override
    public void close() throws IOException {
        // TODO:MQH 2021/2/1 close io
    }
}
