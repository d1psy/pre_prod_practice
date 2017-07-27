package com.epam.golubtsov.commands.webcommands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class WebCommand {

    private String name;

    public WebCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}
