package org.ratel.mybatis.spring.boot.session;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMybatisConfiguration;
import org.ratel.mybatis.spring.boot.session.defaults.DefaultSqlSessionFactory;

/**
 * 构建SqlSessionFactory
 *
 * @author stephen
 * @date 2021/2/1 5:45 下午
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(RatelMybatisConfiguration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
