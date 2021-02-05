package org.ratel.mybatis.spring.boot.parsing;

/**
 * 静态sql文本
 *
 * @author stephen
 * @date 2021/2/4 4:16 下午
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }

    @Override
    public boolean isDynamic() {
        return false;
    }
}
