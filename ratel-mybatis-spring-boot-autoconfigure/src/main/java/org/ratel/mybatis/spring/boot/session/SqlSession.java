package org.ratel.mybatis.spring.boot.session;

import java.util.List;

/**
 * 用于执行sql，管理事物
 *
 * @author stephen
 * @date 2021/2/1 4:46 下午
 */
public interface SqlSession {

    /**
     * 查询一条记录
     *
     * @param statementId
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId, Object parameter);

    /**
     * 查询多条条记录
     *
     * @param statementId
     * @param parameter
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String statementId, Object parameter);
}
