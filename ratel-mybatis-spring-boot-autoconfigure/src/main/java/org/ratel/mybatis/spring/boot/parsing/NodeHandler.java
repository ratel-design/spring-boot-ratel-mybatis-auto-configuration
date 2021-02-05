package org.ratel.mybatis.spring.boot.parsing;

import org.dom4j.Node;

/**
 * 处理CRUD标签的子标签
 *
 * @author stephen
 * @date 2021/2/2 11:05 上午
 */
public abstract class NodeHandler {

    private NodeHandler next;

    protected NodeHandler setNext(NodeHandler next) {
        this.next = next;
        return this.next;
    }

    protected SqlNode nextHandle(Node node) {
        if (next == null) {
            return null;
        }
        return next.handleNode(node);
    }

    /**
     * 解析处理文档节点
     *
     * @param node
     * @return
     */
    public abstract SqlNode handleNode (Node node);

}
