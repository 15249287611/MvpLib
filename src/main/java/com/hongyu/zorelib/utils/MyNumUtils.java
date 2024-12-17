package com.hongyu.zorelib.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * 数字转换工具
 */
public class MyNumUtils {

    /**
     * 单位进位，中文默认为4位即（万、亿）
     */
    public static int UNIT_STEP = 4;

    /**
     * 单位
     */
    public static String[] CN_UNITS = new String[]{"个", "十", "百", "千", "万", "十",
            "百", "千", "亿", "十", "百", "千", "万"};

    /**
     * 汉字
     */
    public static String[] CN_CHARS = new String[]{"零", "一", "二", "三", "四",
            "五", "六", "七", "八", "九"};


    /**
     * 将数值转换为中文
     *
     * @param num          需要转换的数值
     * @param isColloquial 是否口语化。例如12转换为'十二'而不是'一十二'。
     */
    public String[] convert(long num, boolean isColloquial) {
        if (num < 10) {// 10以下直接返回对应汉字
            return new String[]{CN_CHARS[(int) num]};// ASCII2int
        }

        char[] chars = String.valueOf(num).toCharArray();
        if (chars.length > CN_UNITS.length) {// 超过单位表示范围的返回空
            return new String[]{};
        }

        boolean isLastUnitStep = false;// 记录上次单位进位
        ArrayList<String> cnChars = new ArrayList<>(chars.length * 2);// 创建数组，将数字填入单位对应的位置
        for (int pos = chars.length - 1; pos >= 0; pos--) {// 从低位向高位循环
            char ch = chars[pos];
            String cnChar = CN_CHARS[ch - '0'];// ascii2int 汉字
            int unitPos = chars.length - pos - 1;// 对应的单位坐标
            String cnUnit = CN_UNITS[unitPos];// 单位
            boolean isZero = (ch == '0');// 是否为0
            boolean isZeroLow = (pos + 1 < chars.length && chars[pos + 1] == '0');// 是否低位为0

            boolean isUnitStep = (unitPos >= UNIT_STEP && (unitPos % UNIT_STEP == 0));// 当前位是否需要单位进位

            if (isUnitStep && isLastUnitStep) {// 去除相邻的上一个单位进位
                int size = cnChars.size();
                cnChars.remove(size - 1);
                if (!CN_CHARS[0].equals(cnChars.get(size - 2))) {// 补0
                    cnChars.add(CN_CHARS[0]);
                }
            }

            if (isUnitStep || !isZero) {// 单位进位(万、亿)，或者非0时加上单位
                cnChars.add(cnUnit);
                isLastUnitStep = isUnitStep;
            }
            if (isZero && (isZeroLow || isUnitStep)) {// 当前位为0低位为0，或者当前位为0并且为单位进位时进行省略
                continue;
            }
            cnChars.add(cnChar);
            isLastUnitStep = false;
        }

        Collections.reverse(cnChars);
        // 清除最后一位的0
        int chSize = cnChars.size();
        String chEnd = cnChars.get(chSize - 1);
        if (CN_CHARS[0].equals(chEnd) || CN_UNITS[0].equals(chEnd)) {
            cnChars.remove(chSize - 1);
        }

        // 口语化处理
        if (isColloquial) {
            String chFirst = cnChars.get(0);
            String chSecond = cnChars.get(1);
            if (chFirst.equals(CN_CHARS[1]) && chSecond.startsWith(CN_UNITS[1])) {// 是否以'一'开头，紧跟'十'
                cnChars.remove(0);
            }
        }
        return cnChars.toArray(new String[]{});
    }

    /**
     * 格式化数字，用逗号分割
     *
     * @param number 1000000.7569 to 1,000,000.76 or
     * @return 格式化完成的字符串
     */
    public static String formatNum4(double number) {
        DecimalFormat decimalFormat;
        if (number == (long) number) {
            decimalFormat = new DecimalFormat("#,##0");
        } else {
            decimalFormat = new DecimalFormat("#,##0.00");
        }
        return decimalFormat.format(number);
    }

    /**
     * 精确到小数点后两位数
     */
    public static String get2Num(double num) {
        return format("##0.00", num);
    }


    /**
     * 精确到小数点后两位数，同时保留原有的正负号，例如：
     * 1. "80%" -> "80.00%"
     * 2. "-80" -> "-80.00"
     */
    public static String get2Num(String num) {
        if (TextUtils.isEmpty(num)) {
            return AppCons.STR_DEFAULT;
        }
        try {
            // 判断正负号
            String sign = "";
            if (num.startsWith("+") || num.startsWith("-")) {
                sign = num.substring(0, 1);
                num = num.substring(1); // 去掉符号部分
            }

            if (num.contains("%")) {
                // 处理带有百分号的字符串
                double value = Double.parseDouble(num.replace("%", "").trim());
                return String.format(Locale.getDefault(), "%s%.2f%%", sign, value);
            } else {
                // 处理不带百分号的字符串
                double value = Double.parseDouble(num);
                return String.format(Locale.getDefault(), "%s%.2f", sign, value);
            }
        } catch (NumberFormatException e) {
            return AppCons.STR_DEFAULT; // 无法解析时返回默认值
        }
    }

    /**
     * 精确到小数点后两位数带符号 + -
     */
    public static String get2NumUnit(String num) {
        if (TextUtils.isEmpty(num)) {
            return AppCons.STR_DEFAULT;
        }
        double number = Double.parseDouble(num);
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        if (number > 0) {
            return "+" + decimalFormat.format(number);
        } else if (number < 0) {
            return decimalFormat.format(number);
        } else {
            return "0.00";
        }
    }

    /**
     * 大于 0，前缀加号 "-";
     * 小于 0，前缀为负号 "+";
     * 等于 0，正常返回。
     */
    public static String get2NumUnitReversed(String num) {
        if (TextUtils.isEmpty(num)) {
            return AppCons.STR_DEFAULT;
        }

        double number = -Double.parseDouble(num);
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");

        if (number > 0) {
            return "+" + decimalFormat.format(number);
        } else if (number < 0) {
            return decimalFormat.format(number);
        } else {
            return "0.00";
        }
    }


    /**
     * 精确到小数点后一位数
     */
    public static String get1Num(double num) {
        return format("##0.0", num);
    }

    public static String format(String pattern, double number) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(number);
    }

    /**
     * @return >99 返99+
     */
    public static String formatNum99(int num) {
        return num > 99 ? "99+" : String.valueOf(num);
    }


    /**
     * return 判断百分比是否是正数,负数,0
     */
    public static double checkPercentage(String value) {
        double numberValue;
        try {
            numberValue = value.contains("%")
                    ? Double.parseDouble(value.replace("%", ""))
                    : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
        return numberValue;
    }


}