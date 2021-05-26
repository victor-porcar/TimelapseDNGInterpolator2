package com.github.victormpcmun.dngsettingsinterpolator.util;

public  class StringUtil {

    public static String inBetweenExcluding(String s, String s1, String s2) {
        int indexS1 = s.indexOf(s1);
        if (indexS1<0) {
            return null;
        }

        int indexS2= s.indexOf(s2, indexS1 + s1.length());

        if (indexS2<0) {
            return null;
        }

        String result = s.substring(indexS1 + s1.length(), indexS2);
        return result;
    }

    public static String replaceInBetween(String s, String s1, String s2, String replacement) {
        int indexS1 = s.indexOf(s1);
        if (indexS1<0) {
            return null;
        }

        int indexS2= s.indexOf(s2, indexS1 + s1.length());

        if (indexS2<0) {
            return null;
        }

        String result = s.substring(0,indexS1 + s1.length()) + replacement + s.substring(indexS2);
        return result;
    }

    public static String inBetweenIncluding(String s, String s1, String s2) {
        return s1 + inBetweenExcluding(s, s1, s2) + s2;
    }


    public static String getRepeatedString(String text, int repetitions) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<repetitions;i++) {
            sb.append(text);
        }
        return sb.toString();
    }

}
