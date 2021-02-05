package org.ratel.mybatis.demo.po;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author stephen
 * @date 2021/2/1 2:59 下午
 */
@Data
public class User {

    private int id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

}
