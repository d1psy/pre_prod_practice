package com.epam.golubtsov.view;

public abstract class View implements JSONView {

    private String out;

    public View() {
    }

    public View(String out) {
        this.out = out;
    }

    public String getOut() {
        return out;
    }

    @Override
    public String toString() {
        return getOut();
    }
}
