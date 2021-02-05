package org.ratel.mybatis.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据源配置
 *
 * @author stephen
 * @date 2021/2/5 6:00 下午
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class RatelDatasource {
    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClass;
}
