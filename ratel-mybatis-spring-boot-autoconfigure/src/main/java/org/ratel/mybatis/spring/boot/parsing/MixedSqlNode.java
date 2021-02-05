package org.ratel.mybatis.spring.boot.parsing;

import java.util.List;

/**
 * 汇集所有Node
 *
 * @author stephen
 * @date 2021/2/3 7:55 下午
 */
public class MixedSqlNode implements SqlNode {

    /**
     * 汇集所有Node
     * 将嵌套node拆分后放入集合
     */
    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(context);
        }
    }

    @Override
    public boolean isDynamic() {
        for (SqlNode sqlNode : sqlNodes) {
            if (sqlNode.isDynamic()) {
                return true;
            }
        }
        return false;
    }
}
