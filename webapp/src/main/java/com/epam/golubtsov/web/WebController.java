package com.epam.golubtsov.web;

import com.epam.golubtsov.commands.webcommands.RegisterPageCommand;
import com.epam.golubtsov.commands.webcommands.WebCommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class WebController extends HttpServlet {

    private WebCommandContainer container = new WebCommandContainer();

    @Override
    public void init() throws ServletException {
        container.addCommand(new RegisterPageCommand("registered"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = container.processCommand(req, resp);
        req.getRequestDispatcher(result).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = container.processCommand(req, resp);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
