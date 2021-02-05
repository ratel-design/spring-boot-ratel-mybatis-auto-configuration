package org.ratel.mybatis.spring.boot.parsing;

import org.ratel.mybatis.spring.boot.util.OgnlUtils;

/**
 * 动态sql与对象关联处理
 *
 * @author stephen
 * @date 2021/2/4 7:51 下午
 */
public class DynamicTokenHandler implements TokenHandler {

    private Object params;

    public DynamicTokenHandler(Object params) {
        this.params = params;
    }

    @Override
    public String handle(String replaceKey) {
        if (params == null) {
            return "";
        }
        if (params.getClass().isPrimitive()) {
            return String.valueOf(params);
        }
        Object replaceVal = OgnlUtils.getValue(replaceKey, params);
        return replaceVal == null ? "" : String.valueOf(replaceVal);
    }
}
