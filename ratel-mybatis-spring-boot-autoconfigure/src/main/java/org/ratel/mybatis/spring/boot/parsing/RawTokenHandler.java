package org.ratel.mybatis.spring.boot.parsing;

import org.ratel.mybatis.spring.boot.sql.source.ParameterMapping;

import java.util.List;

/**
 * 非动态sql与对象关联处理
 *
 * @author stephen
 * @date 2021/2/4 7:57 下午
 */
public class RawTokenHandler implements TokenHandler{

    private List<ParameterMapping> parameterMappings;

    public RawTokenHandler(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    @Override
    public String handle(String replaceKey) {
        ParameterMapping mapping = new ParameterMapping();
        mapping.setName(replaceKey);
        parameterMappings.add(mapping);
        return "?";
    }
}
