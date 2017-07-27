package com.epam.golubtsov.util;

import java.text.MessageFormat;

public class JSONGenerator {
    private StringBuilder buffer = new StringBuilder();
    private String KEY_VALUE_PATTERN = "{0}:{1},";

    public JSONGenerator() {
        addJsonStart();
    }

    public JSONGenerator addPair(Object key, Object value) {
        buffer.append(MessageFormat.format(KEY_VALUE_PATTERN, String.valueOf(key), String.valueOf(value)));
        return this;
    }

    private void addJsonStart() {
        buffer.append("{");
    }

    public String generate() {
        removeLastComma();
        buffer.append("}");
        String result = buffer.toString();
        buffer = new StringBuilder();
        return result;
    }

    private void removeLastComma() {
        int lastIndex = buffer.lastIndexOf(",");
        if (lastIndex != -1) {
            buffer.deleteCharAt(lastIndex);
        }
    }
}
