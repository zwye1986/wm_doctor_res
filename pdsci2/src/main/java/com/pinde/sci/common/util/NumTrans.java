package com.pinde.sci.common.util;

import com.pinde.core.util.StringUtil;

import java.math.BigDecimal;


public class NumTrans {
    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖"};
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟"};
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    /**
     * 阿拉伯数字转换为中文小写数字
     */
    public static String transNum(String mum) {
        String[] aa = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿"};
        String[] bb = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String newNum = "";
        char[] ch = mum.toCharArray();
        int maxindex = ch.length;
        //字符的转换
        //两位数的特殊转换
        if (maxindex == 2) {
            for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
                if (ch[j] != 48) {
                    if (j == 0 && ch[j] == 49) {
                        newNum += aa[i];
                    } else {
                        newNum += bb[ch[j] - 49] + aa[i];
                    }
                }
            }
            //其他位数的特殊转换，使用的是int类型最大的位数为十亿
        } else {
            for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
                if (ch[j] != 48) {
                    newNum += bb[ch[j] - 49] + aa[i];
                }
            }
        }
        return newNum;
    }

    /**
     * 两数相除得百分比
     *
     * @param num1
     * @param num2
     * @param decimalNum(保留小数点位数)
     * @return
     */
    public static String transPercent(String num1, String num2, Integer decimalNum) {
        String newNum = "";
        if (StringUtil.isBlank(num1)) {
            num1 = "0";
        }
        Float f = null;
        if (StringUtil.isBlank(num2) || "0".equals(num2)|| "0.0".equals(num2)) {
            f = Float.valueOf("0");
        } else {
            f = (Float.valueOf(num1) / Float.valueOf(num2)) * 100;
        }
        BigDecimal b = new BigDecimal(f).setScale(decimalNum, BigDecimal.ROUND_HALF_UP);
        newNum = String.valueOf(b) + "%";
        return newNum;
    }

    /**
     * 两数相除得百分比
     *
     * @param num1
     * @param decimalNum(保留小数点位数)
     * @return
     */
    public static String transPercent(String num1, Integer decimalNum) {
        BigDecimal b = new BigDecimal(num1).setScale(decimalNum, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(b);
    }

    public static String transMultiply(String num1, Integer multiplier){
        try{
            BigDecimal b = new BigDecimal(num1).multiply(new BigDecimal(multiplier)).setScale(2,BigDecimal.ROUND_HALF_UP);
            return String.valueOf(b);
        } catch (Exception e){
           return num1;
        }
    }

    /**
     * 数字转换为汉语中人民币的大写
     *
     * @param numberOfMoney
     * @return
     */
    public static String NumberToCN(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
            if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }
}