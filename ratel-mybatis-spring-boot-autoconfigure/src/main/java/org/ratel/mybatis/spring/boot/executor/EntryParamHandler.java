package org.ratel.mybatis.spring.boot.executor;

import org.ratel.mybatis.spring.boot.sql.source.ParameterMapping;
import org.ratel.mybatis.spring.boot.util.OgnlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义对象类型参数处理
 *
 * @author stephen
 * @date 2021/2/5 4:39 下午
 */
public class EntryParamHandler extends ParamHandler {

    private Class<?> parameterClass;
    private List<ParameterMapping> parameterMappings;

    public EntryParamHandler(Class<?> parameterClass, List<ParameterMapping> parameterMappings) {
        this.parameterClass = parameterClass;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public List<Object> handle(Object parameter) {
        if (parameterClass.equals(parameter.getClass())) {
            List<Object> list = new ArrayList();
            for (ParameterMapping parameterMapping : parameterMappings) {
                Object value = OgnlUtils.getValue(parameterMapping.getName(), parameter);
                list.add(value);
            }
            return list;
        }
        return nextHandle(parameter);
    }
}
