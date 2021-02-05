package org.ratel.mybatis.spring.boot.util;

import ognl.Ognl;
import ognl.OgnlContext;

import java.math.BigDecimal;

/**
 * 使用ognl解析对象
 *
 * @author stephen
 * @date 2021/2/4 3:31 下午
 */
public class OgnlUtils {

    /**
     * 根据ognl表达式获取对象属性的值
     *
     * @param expression ognl表达式
     * @param obj
     * @return 匹配对象的值
     */
    public static Object getValue(String expression, Object obj) {
        if (obj == null || expression == null || expression.isEmpty()) {
            return null;
        }
        try {
            OgnlContext context = new OgnlContext();
            context.setRoot(obj);
            // 构建Ognl表达式
            Object ognlExpression = Ognl.parseExpression(expression);
            // 解析表达式
            return Ognl.getValue(ognlExpression, context, context.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据对象属性值，判断ognl表达式是否成立
     *
     * @param expression ognl表达式
     * @param obj
     * @return boolean
     */
    public static boolean evaluateBoolean(String expression, Object obj) {
        Object value = getValue(expression, obj);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            return new BigDecimal(String.valueOf(value)).compareTo(BigDecimal.ZERO) != 0;
        }
        return value != null;
    }

}
