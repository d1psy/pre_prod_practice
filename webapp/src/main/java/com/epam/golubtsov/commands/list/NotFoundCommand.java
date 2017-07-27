package com.epam.golubtsov.commands.list;

import com.epam.golubtsov.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFoundCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "error.html";
    }
}
