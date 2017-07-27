package com.epam.golubtsov.commands.webcommands;

import com.epam.golubtsov.entity.User;
import com.epam.golubtsov.service.UserService;
import com.epam.golubtsov.service.api.UserServiceImpl;
import com.epam.golubtsov.util.StringUtils;
import com.epam.golubtsov.web.captcha.factory.ContextCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet("/register")
public class RegisterCommand extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = execute(req, resp);
        req.getRequestDispatcher(result).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = execute(req, resp);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    public String execute (HttpServletRequest request, HttpServletResponse response) {
        if (!checkRegistrationInput(request)) {
            return addRegistrationParameter(request, "registered.jsp", "name", "email");
        }
        ContextCaptcha contextCaptcha = new ContextCaptcha();
        String inputCaptcha = request.getParameter("captcha");
        String expectedCaptcha = contextCaptcha.getCaptcha(request);
        if (!inputCaptcha.equals(expectedCaptcha)) {
           return addRegistrationParameter(request, "registered.jsp", "name", "email");
        }
        if (userService.exists(request.getParameter("email"))) {
            return addRegistrationParameter(request, "registered.jsp", "name", "email");
        }
        if (userService.add(get(request))) {
            return "index.jsp";
        } else {
            return addRegistrationParameter(request, "registered.jsp", "name", "email");
        }
    }

    private String addRegistrationParameter(HttpServletRequest request, String url, String... input) {
        url += "?";
        for (String s : input) {
            String param = request.getParameter(s);
            if (!param.equals("")) {
                url += s + "=" + param + "&";
            }
        }
        url = url.substring(0, url.length() - 1);
        return url;
    }

    private boolean checkRegistrationInput(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return !StringUtils.isEmptyOrNull(name, email) && !(name.length() < 3 || !email.contains("@")) &&
                !(password.length() < 3);
    }

    private User get(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        try {
            user.setIcon(getIcon(request));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getIcon(HttpServletRequest request) throws IOException, ServletException {
        final String path = (String) request.getServletContext().getAttribute("FILES_DIR");
        final Part filePart = request.getPart("file");
        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }
        String fileName = getFileName(filePart);
        fileName = fileName.replaceAll(".*\\.", request.getParameter("email") + ".");

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
        return fileName;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
