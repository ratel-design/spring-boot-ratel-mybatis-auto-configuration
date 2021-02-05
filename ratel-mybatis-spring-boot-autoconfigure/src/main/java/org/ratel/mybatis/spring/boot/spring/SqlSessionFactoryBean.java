package org.ratel.mybatis.spring.boot.spring;

import org.dom4j.DocumentException;
import org.ratel.mybatis.spring.boot.autoconfigure.RatelMybatisConfiguration;
import org.ratel.mybatis.spring.boot.builder.xml.XMLMapperBuilder;
import org.ratel.mybatis.spring.boot.session.SqlSessionFactory;
import org.ratel.mybatis.spring.boot.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author stephen
 * @date 2021/2/1 4:52 下午
 */
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    private RatelMybatisConfiguration configuration;
    private DataSource dataSource;
    /**
     * 所有mapper.xml配置文件
     * -- 根据配置路径读取
     */
    private Resource[] mapperLocations;

    @Override
    public SqlSessionFactory getObject() throws Exception {
        if (this.sqlSessionFactory == null) {
            afterPropertiesSet();
        }
        return this.sqlSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    public RatelMybatisConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(RatelMybatisConfiguration configuration) {
        this.configuration = configuration;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.sqlSessionFactory = buildSqlSessionFactory();
    }

    private SqlSessionFactory buildSqlSessionFactory() throws Exception {
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        // 解析所有读取到的mapper.xml
        for (Resource mapperLocation : mapperLocations) {
            try {
                xmlMapperBuilder.parse(mapperLocation.getInputStream());
            } catch (DocumentException | IOException ex) {
                throw new NestedIOException("Failed to parse mapper resource: " + mapperLocation, ex);
            }
        }
        // 创建session工厂，创建的每个session都可以拿到configuration，从中获取想要statement对象，然后执行
        return this.sqlSessionFactoryBuilder.build(configuration);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }

    public void setMapperLocations(Resource... mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public Resource[] getMapperLocations() {
        return mapperLocations;
    }
}
