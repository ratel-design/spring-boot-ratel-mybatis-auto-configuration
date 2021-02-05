package org.ratel.mybatis.spring.boot.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * 文档工具类
 *
 * @author stephen
 * @date 2021/2/1 4:02 下午
 */
public class DocumentUtils {

    public static Document readDocument(InputStream inputStream) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        return document;
    }
}
