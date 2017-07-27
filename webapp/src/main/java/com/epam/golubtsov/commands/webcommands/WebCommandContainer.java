package com.epam.golubtsov.commands.webcommands;

import com.epam.golubtsov.commands.list.NotFoundCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class WebCommandContainer {

    private static Map<String, WebCommand> commands = new HashMap();
    private NotFoundCommand noCommand = new NotFoundCommand();

    public void addCommand(WebCommand command) {
        commands.put(command.getName(), command);
    }

    public String processCommand(HttpServletRequest request, HttpServletResponse response) {
        if (commands.containsKey(request.getParameter("command"))) {
            return commands.get(request.getParameter("command")).execute(request, response);
        }
        return noCommand.execute(request, response);
    }

}
