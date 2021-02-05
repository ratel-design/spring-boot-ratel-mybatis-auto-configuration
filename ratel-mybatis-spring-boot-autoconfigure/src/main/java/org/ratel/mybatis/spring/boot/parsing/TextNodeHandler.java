package org.ratel.mybatis.spring.boot.parsing;

import org.dom4j.Node;
import org.dom4j.Text;

/**
 * 文本node处理
 *
 * @author stephen
 * @date 2021/2/4 10:44 上午
 */
public class TextNodeHandler extends NodeHandler {

    @Override
    public SqlNode handleNode(Node node) {
        if (node instanceof Text) {
            String sqlText = node.getText().trim();
            if (sqlText == null || sqlText.equals("")) {
                return null;
            }
            TextSqlNode textSqlNode = new TextSqlNode(sqlText);
            if (textSqlNode.isDynamic()) {
                return textSqlNode;
            } else {
                return new StaticTextSqlNode(sqlText);
            }
        }
        return nextHandle(node);
    }
}
