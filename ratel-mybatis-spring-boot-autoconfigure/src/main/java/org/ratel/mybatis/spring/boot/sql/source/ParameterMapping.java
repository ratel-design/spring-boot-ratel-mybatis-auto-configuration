package org.ratel.mybatis.spring.boot.sql.source;

/**
 * 参数信息
 *
 * @author stephen
 * @date 2021/2/3 5:23 下午
 */
public class ParameterMapping {

    private String name;

    /**
     * 参数类型，暂时未使用
     */
    private Class<?> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
