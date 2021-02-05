package org.ratel.mybatis.spring.boot.executor;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本数据类型参数处理
 *
 * @author stephen
 * @date 2021/2/5 4:34 下午
 */
public class PrimitiveTypeParamHandler extends ParamHandler {

    @Override
    public List<Object> handle(Object parameter) {
        if (parameter.getClass().isPrimitive()) {
            List<Object> list = new ArrayList();
            list.add(parameter);
            return list;
        }
        return nextHandle(parameter);
    }
}
