package org.ratel.mybatis.spring.boot.parsing;

/**
 * 处理sql节点
 *
 * @author stephen
 * @date 2021/2/2 11:11 上午
 */
public interface SqlNode {

    /**
     * 用于执行阶段-拼装sql
     * @param context
     */
    void apply(DynamicContext context);

    /**
     * 是否动态sql
     * 包含${}
     *
     * @return
     */
    boolean isDynamic();
}
