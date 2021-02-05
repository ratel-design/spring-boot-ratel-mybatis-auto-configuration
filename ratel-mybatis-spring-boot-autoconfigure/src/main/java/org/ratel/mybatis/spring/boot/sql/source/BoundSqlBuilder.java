package org.ratel.mybatis.spring.boot.sql.source;

import org.ratel.mybatis.spring.boot.parsing.RawTokenHandler;
import org.ratel.mybatis.spring.boot.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建BoundSql
 *
 * @author stephen
 * @date 2021/2/5 10:21 上午
 */
public class BoundSqlBuilder {

    /**
     * 包含#{key}
     */
    private String sqlText;

    public BoundSqlBuilder(String sqlText) {
        this.sqlText = sqlText;
    }

    public BoundSql build() {
        List<ParameterMapping> parameterMappings = new ArrayList<>();
        String sql = StringUtils.bindingToken(sqlText, "#{", "}", new RawTokenHandler(parameterMappings));
        return new BoundSql(sql, parameterMappings);
    }
}
