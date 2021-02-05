package org.ratel.mybatis.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author stephen
 * @date 2021/2/5 11:38 上午
 */
@Configuration
@EnableConfigurationProperties(RatelDatasource.class)
public class Config {

    @Autowired
    private RatelDatasource ratelDatasource;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(ratelDatasource.getJdbcUrl());
        dataSource.setUsername(ratelDatasource.getUsername());
        dataSource.setPassword(ratelDatasource.getPassword());
        dataSource.setDriverClassName(ratelDatasource.getDriverClass());
        return dataSource;
    }
}
