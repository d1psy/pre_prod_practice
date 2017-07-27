package com.epam.golubtsov.web.captcha.factory;


import com.epam.golubtsov.entity.Captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextCaptcha implements CaptchaManager {

    @Override
    public void setCaptcha(Captcha captcha, HttpServletRequest request, HttpServletResponse res) {
        Cookie captchaIdCookie = new Cookie("idcaptcha", captcha.getId());
        captchaIdCookie.setMaxAge(1000 * 60);
        res.addCookie(captchaIdCookie);
        request.getServletContext()
                .setAttribute(captcha.getId(), captcha);
    }

    @Override
    public String getCaptcha(HttpServletRequest request) {
        Cookie cookie = findCookie("idcaptcha", request.getCookies());
        if (cookie!=null) {
            String captchaId = cookie.getValue();
            Captcha captcha = (Captcha) request.getServletContext().getAttribute(captchaId);
            return captcha.getText();
        }
        return "";
    }

    private Cookie findCookie(String name, Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }
}
