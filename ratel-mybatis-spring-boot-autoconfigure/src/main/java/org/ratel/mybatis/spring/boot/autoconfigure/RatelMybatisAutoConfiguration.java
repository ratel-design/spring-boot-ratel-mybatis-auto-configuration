package org.ratel.mybatis.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 *
 * @author stephen
 * @date 2021/1/26 1:18 下午
 */
@Configuration
@EnableConfigurationProperties(RatelMybatisProperties.class)
public class RatelMybatisAutoConfiguration {


}
