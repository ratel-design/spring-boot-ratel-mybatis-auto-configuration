package org.ratel.mybatis.spring.boot.executor;

import java.util.List;

/**
 * 处理参数
 *
 * @author stephen
 * @date 2021/2/5 4:24 下午
 */
public abstract class ParamHandler {

    private ParamHandler next;

    protected ParamHandler setNext(ParamHandler next) {
        this.next = next;
        return this.next;
    }

    protected List<Object> nextHandle(Object parameter) {
        if (next == null) {
            return null;
        }
        return next.handle(parameter);
    }

    /**
     * 处理参数
     *
     * @param parameter
     * @return
     */
    public abstract List<Object> handle(Object parameter);
}
