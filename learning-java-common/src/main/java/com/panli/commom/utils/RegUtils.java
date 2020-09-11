package com.panli.commom.utils;

/**
 * @author lipan
 * @date 2020-09-11
 * @desc 正则表达式工具类
 */
public class RegUtils {

    private static final String CODE_REG = "[a-zA-Z0-9_]+";

    /**
     * 判断字符串str是否满足reg的正则表达式
     *
     * @param str
     * @param reg
     * @return
     */
    public static boolean valid(String str, String reg) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(reg))
            return false;
        return str.matches(reg);
    }
}
