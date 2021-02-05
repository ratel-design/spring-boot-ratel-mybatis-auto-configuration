package org.ratel.mybatis.spring.boot.parsing;

import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * if标签处理
 *
 * @author stephen
 * @date 2021/2/2 11:43 上午
 */
public class IfNodeHandler extends NodeHandler {

    @Override
    public SqlNode handleNode(Node node) {
        Element nodeElement = (Element) node;
        String name = nodeElement.getName();
        if (name == null) {
            return null;
        }
        if ("if".equals(name)) {
            String test = nodeElement.attributeValue("test");
            int  subNodeCount = nodeElement.nodeCount();
            List<SqlNode> contents = new ArrayList<>();
            for (int i = 0; i < subNodeCount; i++) {
                SqlNode sqlNode = new NodeHandlerChain(nodeElement.node(i)).handle();
                if (sqlNode != null) {
                    contents.add(sqlNode);
                }
            }
            if (CollectionUtils.isEmpty(contents)) {
                return null;
            }
            return new IfSqlNode(test, new MixedSqlNode(contents));
        }
        return nextHandle(node);
    }
}
