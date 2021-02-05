package org.ratel.mybatis.spring.boot.autoconfigure;

import org.dom4j.Document;
import org.dom4j.Element;
import org.ratel.mybatis.spring.boot.session.SqlSessionFactory;
import org.ratel.mybatis.spring.boot.spring.SqlSessionFactoryBean;
import org.ratel.mybatis.spring.boot.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;

/**
 * mybatis配置类
 *
 * @author stephen
 * @date 2021/1/26 1:18 下午
 */
@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(RatelMybatisProperties.class)
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class RatelMybatisAutoConfiguration {

    @Autowired
    private RatelMybatisProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setConfiguration(new RatelMybatisConfiguration(dataSource));
        factory.setDataSource(dataSource);
        Resource[] mapperLocations = this.properties.resolveMapperLocations();
        if (!ObjectUtils.isEmpty(mapperLocations)) {
            factory.setMapperLocations(mapperLocations);
        }
        return factory.getObject();
    }
}



