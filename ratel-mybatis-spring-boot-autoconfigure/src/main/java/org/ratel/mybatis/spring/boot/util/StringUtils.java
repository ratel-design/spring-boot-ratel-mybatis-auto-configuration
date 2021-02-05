package org.ratel.mybatis.spring.boot.util;

import org.ratel.mybatis.spring.boot.parsing.TokenHandler;

/**
 * 处理字符串
 *
 * @author stephen
 * @date 2021/2/4 11:24 上午
 */
public class StringUtils {


    /**
     * 将文本中的关键字替换为对象中的属性对应的值
     * 例：文本中有${username}，会将对象 {@code token} 中的username键的值替换到文本中，如未找到该键则将文本中替换成 ""
     *
     * @param text 文本
     * @param prefix 识别文本key的前缀，例：${
     * @param suffix 识别文本key的后缀，例：}
     * @param tokenHandler 处理参数与对象关联
     * @return 替换后的文本
     */
    public static String bindingToken(String text, String prefix, String suffix, TokenHandler tokenHandler) {
        if (text == null || text.isEmpty() || prefix == null || prefix.isEmpty() || suffix == null || suffix.isEmpty()) {
            return text;
        }
        int prefixIndex;
        int suffixIndex;
        if ((prefixIndex = text.indexOf(prefix)) == -1
                || (suffixIndex = text.indexOf(suffix)) == -1
                || prefixIndex >= suffixIndex) {
            return text;
        }
        char[] textChars = text.toCharArray();
        char[] prefixChars = prefix.toCharArray();
        char[] suffixChars = suffix.toCharArray();
        
        int prefixTemSize = 0,  suffixTemSize = 0;
        boolean matchPrefixBegin = false, matchSuffixBegin = false;
        StringBuilder newText = new StringBuilder();
        StringBuilder tempText = new StringBuilder();
        for (int i = 0; i < textChars.length; i++) {
            // 识别前缀
            if (isMatch(textChars, prefixChars, i)) {
                matchPrefixBegin = true;
                prefixTemSize = prefixChars.length;
            }
            if (prefixTemSize > 0) {
                prefixTemSize--;
                continue;
            }

            // 识别后缀
            if (isMatch(textChars, suffixChars, i)) {
                matchPrefixBegin = false;
                matchSuffixBegin = true;
                suffixTemSize = suffixChars.length;
            }
            if (suffixTemSize > 0) {
                suffixTemSize--;
                continue;
            } else if (matchSuffixBegin) {
                matchSuffixBegin = false;
                // 将文本的关键字替换为对象中的值
                if (tempText.length() > 0) {
                    newText.append(tokenHandler.handle(tempText.toString()));
                    tempText.setLength(0);
                }
            }
            if (matchPrefixBegin) {
                    tempText.append(textChars[i]);
            } else {
                newText.append(textChars[i]);
            }
        }
        if (tempText.length() > 0) {
            newText.append(prefix).append(tempText);
        }
        return newText.toString();
        
    }

    private static boolean isMatch(char[] textChars, char[] matchChars, int textIndex) {
        for (int i = 0; i < matchChars.length; i++) {
            if (matchChars[i] != textChars[textIndex + i]) {
                return false;
            }
        }
        return true;
    }

}
