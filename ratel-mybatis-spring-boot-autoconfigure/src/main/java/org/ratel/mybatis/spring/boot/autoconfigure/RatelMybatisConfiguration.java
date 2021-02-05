package org.ratel.mybatis.spring.boot.autoconfigure;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置文件和映射信息
 *
 * @author stephen
 * @date 2021/2/1 4:09 下午
 */
public class RatelMybatisConfiguration {

    private DataSource dataSource;

    /**
     * 所有解析后的statement - CRUD标签
     */
    private Map<String, RatelMappedStatement> mappedStatements = new HashMap();

    public RatelMybatisConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void addMappedStatement(String statementId, RatelMappedStatement mappedStatement) {
        this.mappedStatements.put(statementId, mappedStatement);
    }

    public RatelMappedStatement getMappedStatement(String statementId) {
        return mappedStatements.get(statementId);
    }
}
