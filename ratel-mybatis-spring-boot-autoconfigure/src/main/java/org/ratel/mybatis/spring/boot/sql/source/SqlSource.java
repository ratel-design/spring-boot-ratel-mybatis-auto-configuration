package org.ratel.mybatis.spring.boot.sql.source;

/**
 * 处理sql语句
 *
 * @author stephen
 * @date 2021/2/1 4:13 下午
 */
public interface SqlSource {

    /**
     * 获取JDBC可执行的sql
     * 包含？及
     * @param params
     * @return
     */
    BoundSql getBoundSql(Object params);

}
