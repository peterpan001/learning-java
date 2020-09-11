package com.panli.commom.utils;

/**
 * @author lipan
 * @date 2020-09-09
 * @desc 字符串工具类
 * @solution 字符串工具类尽量使用commons-lang3
 *
 * <dependency>
 *      <groupId>org.apache.commons</groupId>
 *      <artifactId>commons-lang3</artifactId>
 *      <version>3.4</version>
 * </dependency>
 */
public class StringUtils {

    /**
     * 判断字符串是否不为空
     *
     * @param string
     * @return
     */
    public static boolean isNotBlank(String string) {
        if (string == null || string.trim().length() == 0)
            return false;
        return true;
    }

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isBlank(String string) {
        return !isNotBlank(string);
    }
}
