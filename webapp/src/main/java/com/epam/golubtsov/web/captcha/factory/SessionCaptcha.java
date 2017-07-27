package com.epam.golubtsov.web.captcha.factory;

import com.epam.golubtsov.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCaptcha implements CaptchaManager {

    @Override
    public void setCaptcha(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("idcaptcha", captcha);
    }

    @Override
    public String getCaptcha(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("idcaptcha");
    }
}
