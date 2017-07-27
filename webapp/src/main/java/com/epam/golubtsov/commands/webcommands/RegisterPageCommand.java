package com.epam.golubtsov.commands.webcommands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterPageCommand extends WebCommand {

    public RegisterPageCommand(String registration) {
        super(registration);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "registered.jsp";
    }
}