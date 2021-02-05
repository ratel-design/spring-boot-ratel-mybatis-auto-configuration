package org.ratel.mybatis.spring.boot.parsing;

/**
 * sql关键字与对象关联处理
 *
 * @author stephen
 * @date 2021/2/4 7:46 下午
 */
public interface TokenHandler {

    /**
     * 处理sql中关键字
     *
     * @param replaceKey sql中关键字
     * @return
     */
    String handle(String replaceKey);
}
