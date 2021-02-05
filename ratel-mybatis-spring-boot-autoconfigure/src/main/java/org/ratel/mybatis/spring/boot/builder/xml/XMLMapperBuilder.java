package org.ratel.mybatis.spring.boot.builder.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.ratel.mybatis.spring.boot.autoconfigure.MappedStatementBuilder;
import org.ratel.mybatis.spring.boot.autoconfigure.RatelMappedStatement;
import org.ratel.mybatis.spring.boot.autoconfigure.RatelMybatisConfiguration;
import org.ratel.mybatis.spring.boot.util.DocumentUtils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 解析mapper.xml文件
 *
 * @author stephen
 * @date 2021/2/1 5:55 下午
 */
public class XMLMapperBuilder {

    private RatelMybatisConfiguration configuration;

    public static final String[] STATEMENT_TAG_NAMES = {"select", "update", "insert", "delete"};

    public XMLMapperBuilder(RatelMybatisConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析单个mapper.xml
     *
     * @param inputStream
     * @return
     */
    public RatelMybatisConfiguration parse(InputStream inputStream) throws DocumentException {
        Document document = DocumentUtils.readDocument(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> statementElements = rootElement.elements();
        for (Element statementElement : statementElements) {
            // CRUD标签处理
            if (Arrays.stream(STATEMENT_TAG_NAMES).anyMatch(str -> str.equals(statementElement.getName()))) {
                MappedStatementBuilder mappedStatementBuilder = new MappedStatementBuilder(statementElement);
                RatelMappedStatement mappedStatement = mappedStatementBuilder.builder();
                String statementId = new StringBuilder(namespace).append(".").append(mappedStatement.getStatementId()).toString();
                configuration.addMappedStatement(statementId, mappedStatement);
            }
        }
        return configuration;
    }

}
