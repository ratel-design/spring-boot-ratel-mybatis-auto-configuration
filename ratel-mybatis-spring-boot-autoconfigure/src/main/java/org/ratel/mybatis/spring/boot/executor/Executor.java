package org.ratel.mybatis.spring.boot.executor;

import org.ratel.mybatis.spring.boot.autoconfigure.RatelMappedStatement;
import org.ratel.mybatis.spring.boot.sql.source.BoundSql;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 执行sql
 *
 * @author stephen
 * @date 2021/2/5 3:20 下午
 */
public abstract class Executor {

    private Executor next;

    protected Executor setNext(Executor next) {
        this.next = next;
        return this.next;
    }

    protected Executor getNext() {
        return this.next;
    }

    /**
     * 执行查询
     *
     * @param mappedStatement
     * @param dataSource
     * @param parameter
     * @param boundSql
     * @return
     * @throws SQLException
     */
    public abstract <T> List<T> query(RatelMappedStatement mappedStatement, DataSource dataSource, Object parameter, BoundSql boundSql) throws SQLException;
}
