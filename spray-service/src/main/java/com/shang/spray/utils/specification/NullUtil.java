package com.shang.spray.utils.specification;

/**
 * info:
 * Created by shang on 2017/5/3.
 */
public class NullUtil {
    /**
     * @Fields : 整型默认值，int long byte short
     */
    private static final String INT_DEFAULT = "0";
    /**
     * @Fields : 浮点型默认值，float double
     */
    private static final String FLOAT_DEFAULT = "0.0";
    /**
     * @Fields : char默认值
     */
    private static final char CHAR_DEFAULT = '\0';
    /**
     * @Fields : 空字符串
     */
    private static final String BLANK_STRING = "";

    /**
     * @Title : isEmpty
     * @Description : 判断对象是否为空，不判断boolean类型
     */
    public static boolean isEmpty(Object value) {
        if (null == value) {
            return true;
        }
        String str = String.valueOf(value);
        if (value instanceof String && BLANK_STRING.equals(str)) {
            return true;
        }
        if (INT_DEFAULT.equals(str) || FLOAT_DEFAULT.equals(str)
                || String.valueOf(CHAR_DEFAULT).equals(str)) {
            return true;
        }
        return false;
    }
}
