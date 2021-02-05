package org.ratel.mybatis.spring.boot.sql.source;

import org.ratel.mybatis.spring.boot.parsing.DynamicContext;
import org.ratel.mybatis.spring.boot.parsing.SqlNode;

/**
 * 非动态sql处理
 *
 * @author stephen
 * @date 2021/2/4 5:20 下午
 */
public class RawSqlSource implements SqlSource {

    private BoundSql boundSql;

    public RawSqlSource(SqlNode rootSqlNode) {
        handleSql(rootSqlNode);
    }

    private void handleSql(SqlNode rootSqlNode) {
        // 当前CRUD标签中不含动态部分，只可能出现#{key}
        DynamicContext context = new DynamicContext(null);
        rootSqlNode.apply(context);
        this.boundSql = new BoundSqlBuilder(context.getSql()).build();
    }

    @Override
    public BoundSql getBoundSql(Object params) {
        return this.boundSql;
    }
}
