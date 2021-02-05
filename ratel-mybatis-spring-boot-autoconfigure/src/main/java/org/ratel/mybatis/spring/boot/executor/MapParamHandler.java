package org.ratel.mybatis.spring.boot.executor;

import org.ratel.mybatis.spring.boot.sql.source.ParameterMapping;
import org.ratel.mybatis.spring.boot.util.OgnlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Map类型参数处理
 *
 * @author stephen
 * @date 2021/2/5 4:36 下午
 */
public class MapParamHandler extends ParamHandler {

    private List<ParameterMapping> parameterMappings;

    public MapParamHandler(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    @Override
    public List<Object> handle(Object parameter) {
        if (parameter instanceof Map) {
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
