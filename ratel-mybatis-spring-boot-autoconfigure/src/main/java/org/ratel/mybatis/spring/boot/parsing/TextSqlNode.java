package org.ratel.mybatis.spring.boot.parsing;

import org.ratel.mybatis.spring.boot.util.StringUtils;

/**
 * 文本node
 *
 * @author stephen
 * @date 2021/2/4 11:05 上午
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        DynamicTokenHandler tokenHandler = new DynamicTokenHandler(context.getBindings().get("_parameter"));
        String sql = StringUtils.bindingToken(sqlText, "${", "}", tokenHandler);
        context.appendSql(sql);
    }

    @Override
    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return true;
        }
        return false;
    }

}
