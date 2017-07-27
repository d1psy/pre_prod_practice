package com.epam.golubtsov.web.captcha;

import com.epam.golubtsov.constant.CaptchaConstants;
import com.epam.golubtsov.entity.Captcha;
import com.epam.golubtsov.util.CaptchaGenerator;
import com.epam.golubtsov.util.StringUtils;
import com.epam.golubtsov.web.captcha.factory.CaptchaFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;


@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private CaptchaFactory manager = new CaptchaFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getCaptcha(req, resp);
    }

    private void getCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String text = StringUtils.getRandomString(4);
        String id = request.getSession().getId();
        Captcha captcha = new Captcha(id, text, LocalTime.now(), 2);
        manager.setOption(CaptchaConstants.CONTEXT_CAPTCHA);
        manager.get().setCaptcha(captcha, request, response);
        BufferedImage img = CaptchaGenerator.generate(text);

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(img, "png", os);
        os.close();
    }

}