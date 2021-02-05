package org.ratel.mybatis.spring.boot.sql.source;

import java.util.List;

/**
 * @author stephen
 * @date 2021/2/3 5:21 下午
 */
public class BoundSql {

    /**
     * JDBC可以执行的sql，可能包含？
     */
    private String sql;

    /**
     * 按sql中？顺序对应的参数名及类型
     */
    private List<ParameterMapping> parameterMappings;

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
