package com.epam.golubtsov.web.captcha.factory;


import com.epam.golubtsov.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaManager {

    void setCaptcha(Captcha captcha, HttpServletRequest request, HttpServletResponse response);

    String getCaptcha(HttpServletRequest request);
}
