package com.epam.golubtsov.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    private static Pattern refererPattern = Pattern.compile("GET /.+(?=HTTP)");

    public static String getUrl(String request) {
        Matcher m = refererPattern.matcher(request);
        if (m.find()) {
            String result = m.group();
            return result.split(" ")[1];
        }
        return "";
    }

    public static boolean nullOrEmpty(String name, String password) {
        return name == null || password == null;
    }
}
