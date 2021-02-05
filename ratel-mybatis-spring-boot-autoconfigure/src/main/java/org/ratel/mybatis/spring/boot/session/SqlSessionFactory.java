package org.ratel.mybatis.spring.boot.session;

/**
 * 创建sqlSession
 *
 * @author stephen
 * @date 2021/2/1 4:45 下午
 */
public interface SqlSessionFactory {

    /**
     * 创建一个sqlSession
     *
     * @return sqlSession
     */
    SqlSession openSession();

}
