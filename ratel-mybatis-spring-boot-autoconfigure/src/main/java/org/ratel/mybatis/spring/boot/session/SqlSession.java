package org.ratel.mybatis.spring.boot.session;

import java.io.Closeable;

/**
 * 用于执行sql，管理事物
 *
 * @author stephen
 * @date 2021/2/1 4:46 下午
 */
public interface SqlSession extends Closeable {

    /**
     * 查询一条记录
     *
     * @param statement
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement);
}
