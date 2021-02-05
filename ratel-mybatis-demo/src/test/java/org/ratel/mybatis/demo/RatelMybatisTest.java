package org.ratel.mybatis.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ratel.mybatis.demo.po.User;
import org.ratel.mybatis.spring.boot.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author stephen
 * @date 2021/2/5 10:50 上午
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RatelMybatisTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void test() {
        System.out.println(getUserById(new User(1)));
        User query = new User();
        query.setSex("女");
        System.out.println(getUserById(query));
        System.out.println(getUserById(new User("小")));
        System.out.println(getUserById(new User("小米")));
    }

    private List<User> getUserById(User query) {
        return sqlSessionFactory.openSession().selectList("userMapper.findUserById", query);
    }

}
