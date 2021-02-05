package org.ratel.mybatis.spring.boot.executor;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMappedStatement;
import org.ratel.mybatis.spring.boot.sql.source.BoundSql;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 处理二级缓存
 *
 * @author stephen
 * @date 2021/2/5 3:29 下午
 */
public class CachingExecutor extends Executor {

    @Override
    public <T> List<T> query(RatelMappedStatement mappedStatement, DataSource dataSource, Object parameter, BoundSql boundSql) throws SQLException {
        // 处理二级缓存
        return getNext().query(mappedStatement, dataSource, parameter, boundSql);
    }
}
