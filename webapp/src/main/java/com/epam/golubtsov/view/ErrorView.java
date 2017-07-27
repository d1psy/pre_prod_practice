package com.epam.golubtsov.view;

import com.epam.golubtsov.util.JSONGenerator;

public class ErrorView extends View {

    @Override
    public String getJSON() {
        return new JSONGenerator().addPair("error", getOut()).generate();
    }
    public ErrorView() {

    }

    public ErrorView(String out) {
        super(out);
    }
}