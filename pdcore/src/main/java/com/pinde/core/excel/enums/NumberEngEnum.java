package com.pinde.core.excel.enums;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/10/25/12:12
 * @Description: 0-9 数字对应的字母
 */
public enum NumberEngEnum {
    N0(0,"Z"),
    N1(1,"A"),
    N2(2,"B"),
    N3(3,"C"),
    N4(4,"D"),
    N5(5,"E"),
    N6(6,"F"),
    N7(7,"G"),
    N8(8,"H"),
    N9(9,"I"),
    ;



    public int number;

    public String eng;


    NumberEngEnum(int number, String eng){
        this.number = number;
        this.eng = eng;
    }

    public int getNumber() {
        return number;
    }

    public String getEng() {
        return eng;
    }

    public static String getResult(int num){
        if (num < 10) {
            return getByNumber(num);
        }
        String s = String.valueOf(num);
        char[] charArray = s.toCharArray();
        String result = "";
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            Integer i1 = Integer.valueOf(String.valueOf(c));
            result = result + getByNumber(i1);
        }
        return result;
    }

    public static String getByNumber(int number){
        NumberEngEnum[] values = NumberEngEnum.values();
        for (NumberEngEnum value : values) {
            if (value.getNumber() == number) {
                return value.eng;
            }
        }
        return "";
    }

}
