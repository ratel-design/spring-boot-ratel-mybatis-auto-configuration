package org.ratel.mybatis.spring.boot.sql.source;

import org.ratel.mybatis.spring.boot.parsing.DynamicContext;
import org.ratel.mybatis.spring.boot.parsing.SqlNode;

/**
 * 动态sql处理
 *
 * @author stephen
 * @date 2021/2/4 5:18 下午
 */
public class DynamicSqlSource implements SqlSource {

    /**
     * 单个CRUD标签解析的sqlNode
     */
    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }
    
    @Override
    public BoundSql getBoundSql(Object params) {
        // 处理动态sql，将${key}替换对应入参中的值
        DynamicContext context = new DynamicContext(params);
        rootSqlNode.apply(context);
        // 此时sql中只有#{}，没有${}
        return new BoundSqlBuilder(context.getSql()).build();
    }
}
