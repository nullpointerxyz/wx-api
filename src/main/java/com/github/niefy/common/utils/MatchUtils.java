package com.github.niefy.common.utils;

import java.util.regex.Pattern;

/**
 * @author: 宋开宗
 * @create: 2018-07-14 22:27
 **/
public class MatchUtils {
    private static final Pattern isNumPattern = Pattern.compile("^[0-9]*$");
    private static final Pattern isLetterPattern = Pattern.compile("^[A-Za-z]+$");
    private static final Pattern isCombinationPattern = Pattern.compile("^[0-9]{3}[a-zA-Z0-9][0-9][a-zA-Z0-9][0-9][0-9]{4}[0-9a-zA-z]{0,4}$");
    private static final Pattern isNumLetterPattern = Pattern.compile("^[A-Za-z0-9]+$");

    public static boolean isNum(String word) {
        return isNumPattern.matcher(word).matches();
    }

    public static boolean isLetter(String word) {
        return isLetterPattern.matcher(word).matches();
    }

    public static boolean isCombination(String word) {
        return (word.length() == 11 || word.length() == 15) && isCombinationPattern.matcher(word).matches();
    }


    public static boolean isNumLetter(String word) {
        return isNumLetterPattern.matcher(word).matches();
    }

    public static boolean isChinese(String key) {
        boolean isChinese = true;
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i);
            if (!(c >= 19968 && c <= 40623)) {
                isChinese = false;
                break;
            }
        }
        return isChinese;
    }

    public static boolean noChinese(String key) {
        boolean is = true;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if ((c >= 19968 && c <= 40623)) {
                is = false;
                break;
            }
        }
        return is;
    }

    public static boolean isNumAndChinese(String key) {
        boolean hasNum = false;
        boolean hasChinese = false;
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i);
            if (c <= 57 && c >= 48) {
                hasNum = true;
            }
            if ((c >= 19968 && c <= 40623)) {
                hasChinese = true;
            }
        }
        return hasNum && hasChinese;
    }

    public static boolean isLetterAndChinese(String key) {
        boolean hasLetter = false;
        boolean hasChinese = false;
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i);
            if (c <= 122 && c >= 65) {
                hasLetter = true;
            }
            if ((c >= 19968 && c <= 40623)) {
                hasChinese = true;
            }
        }
        return hasLetter && hasChinese;
    }

    public static boolean isNumLetterAndChinese(String key) {
        boolean hasLetter = false;
        boolean hasChinese = false;
        boolean hasNum = false;
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i);
            if (c <= 122 && c >= 65) {
                hasLetter = true;
            }
            if ((c >= 19968 && c <= 40623)) {
                hasChinese = true;
            }
            if (c <= 57 && c >= 48) {
                hasNum = true;
            }
        }
        return hasLetter && hasChinese && hasNum;
    }

    public static String getNum(String key) {
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i);
            if (c <= 57 && c >= 48) {
                num.append((char) c);
            }
        }
        return num.toString();
    }

    public static String getChinese(String key) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i);
            if (c >= 19968 && c <= 40623) {
                str.append((char) c);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        String s = "1234567";
        System.out.println(noChinese(s));
    }
}
