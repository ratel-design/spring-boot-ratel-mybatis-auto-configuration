package org.ratel.mybatis.spring.boot.parsing;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装参数，拼装sql
 *
 * @author stephen
 * @date 2021/2/2 11:41 上午
 */
public class DynamicContext {

    private StringBuilder sb;
    /**
     * 执行时传入的参数
     * 业务参数、系统参数
     */
    private Map<String, Object> bindings = new HashMap<String, Object>();

    /**
     * 传入业务参数
     * @param parameter
     */
    public DynamicContext(Object parameter) {
        this.bindings.put("_parameter", parameter);
        this.sb = new StringBuilder();
    }

    public String getSql() {
        return sb.toString();
    }

    public void appendSql(String sql) {
        sb.append(sql).append(" ");
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }
}
