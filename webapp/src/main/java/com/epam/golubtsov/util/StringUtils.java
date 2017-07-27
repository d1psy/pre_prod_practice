package com.epam.golubtsov.util;

public class StringUtils {

    public static String getRandomString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += rndChar();
        }
        return result;
    }

    private static char rndChar() {
        int rnd = (int) (Math.random() * 52);
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }

    public static boolean isEmptyOrNull(String... args) {
        for (String arg : args) {
            if (arg == null || arg.equals("")) {
                return true;
            }
        }
        return false;
    }
}