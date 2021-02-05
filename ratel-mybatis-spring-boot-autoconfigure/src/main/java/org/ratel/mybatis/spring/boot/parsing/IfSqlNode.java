package org.ratel.mybatis.spring.boot.parsing;

import org.ratel.mybatis.spring.boot.util.OgnlUtils;

/**
 * if标签node
 *
 * @author stephen
 * @date 2021/2/3 7:40 下午
 */
public class IfSqlNode implements SqlNode {

    private String test;

    private SqlNode subSqlNode;

    public IfSqlNode(String test, SqlNode subSqlNode) {
        this.test = test;
        this.subSqlNode = subSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        if (OgnlUtils.evaluateBoolean(test, context.getBindings().get("_parameter"))) {
            subSqlNode.apply(context);
        }

    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
