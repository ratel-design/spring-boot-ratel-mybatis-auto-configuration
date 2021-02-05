package org.ratel.mybatis.spring.boot.parsing;

import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * node处理链
 * 责任链模式
 *
 * @author stephen
 * @date 2021/2/4 4:19 下午
 */
public class NodeHandlerChain {

    private Node node;

    public NodeHandlerChain(Node node) {
        this.node = node;
    }

    /**
     * 使用责任链模式依次处理不同类型节点
     *
     * @return
     */
    public SqlNode handle() {
        List<SqlNode> contents = new ArrayList<>();
        TextNodeHandler textNodeHandler = new TextNodeHandler();
        IfNodeHandler ifNodeHandler = new IfNodeHandler();
        // TODO:MQH 2021/2/4 where、set ...
        textNodeHandler.setNext(ifNodeHandler);
        textNodeHandler.handleNode(node);
        return new MixedSqlNode(contents);
    }
}
