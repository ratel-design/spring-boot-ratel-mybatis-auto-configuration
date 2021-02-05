package org.ratel.mybatis.spring.boot.autoconfigure;

import org.dom4j.Element;
import org.dom4j.Node;
import org.ratel.mybatis.spring.boot.parsing.MixedSqlNode;
import org.ratel.mybatis.spring.boot.parsing.NodeHandlerChain;
import org.ratel.mybatis.spring.boot.parsing.SqlNode;
import org.ratel.mybatis.spring.boot.sql.source.DynamicSqlSource;
import org.ratel.mybatis.spring.boot.sql.source.RawSqlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建单个Statement
 * 解析CRUD标签
 *
 * @author stephen
 * @date 2021/2/2 11:45 上午
 */
public class MappedStatementBuilder {

    private Element statementElement;

    public MappedStatementBuilder(Element statementElement) {
        this.statementElement = statementElement;
    }

    /**
     * 构建statement
     *
     * @return
     */
    public RatelMappedStatement builder() {
        RatelMappedStatement ratelMappedStatement = new RatelMappedStatement();
        ratelMappedStatement.setStatementId(statementElement.attributeValue("id"));
        String statementType = statementElement.attributeValue("statementType");
        ratelMappedStatement.setStatementType(statementType == null || statementType == "" ? "PREPARED" : statementType);
        builderParameterTypeClass(ratelMappedStatement);
        builderResultTypeClass(ratelMappedStatement);
        // 解析sql
        builderSqlSource(ratelMappedStatement);

        return ratelMappedStatement;
    }

    private void builderParameterTypeClass(RatelMappedStatement ratelMappedStatement) {
        String parameterType = statementElement.attributeValue("parameterType");
        try {
            Class<?> clazz = Class.forName(parameterType);
            ratelMappedStatement.setParameterTypeClass(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void builderResultTypeClass(RatelMappedStatement ratelMappedStatement) {
        String resultType = statementElement.attributeValue("resultType");
        try {
            Class<?> clazz = Class.forName(resultType);
            ratelMappedStatement.setResultTypeClass(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析sql
     * 标签处理 if、where、set ...
     * sql拼装 -- ${}替换值，#{}替换为？，? 到执行阶段再替换
     * ${} 每次都需要解析再执行，#{}解析一次，两者都包含也是需要每次解析
     *
     * @param ratelMappedStatement
     */
    private void builderSqlSource(RatelMappedStatement ratelMappedStatement) {
        int rootNodeCount = statementElement.nodeCount();
        List<SqlNode> contents = new ArrayList<>();
        for (int i = 0; i < rootNodeCount; i++) {
            Node node = statementElement.node(i);
            SqlNode sqlNode = new NodeHandlerChain(node).handle();
            if (sqlNode != null) {
                contents.add(sqlNode);
            }
        }
        SqlNode sqlNode = new MixedSqlNode(contents);
        if (sqlNode.isDynamic()) {
            ratelMappedStatement.setSqlSource(new DynamicSqlSource(sqlNode));
        } else {
            ratelMappedStatement.setSqlSource(new RawSqlSource(sqlNode));
        }
    }
}
