package org.ratel.mybatis.spring.boot.executor;

import org.ratel.mybatis.spring.boot.sql.source.ParameterMapping;

import java.util.List;

/**
 * 参数处理链
 *
 * @author stephen
 * @date 2021/2/5 4:40 下午
 */
public class ParamHandlerChain {

    /**
     * 入参值解析为list
     * 责任链模式处理
     *
     * @param parameter
     * @param parameterMappings
     * @param paramClass
     * @return
     */
    public List<Object> handle(Object parameter, List<ParameterMapping> parameterMappings, Class<?> paramClass) {
        ParamHandler paramHandler = new PrimitiveTypeParamHandler();
        paramHandler.setNext(new MapParamHandler(parameterMappings)).setNext(new EntryParamHandler(paramClass, parameterMappings));
        return paramHandler.handle(parameter);
    }
}
