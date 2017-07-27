package com.epam.golubtsov.web.captcha;

import com.epam.golubtsov.web.captcha.factory.CaptchaFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

public class CaptchaServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String captchaMode = servletContextEvent.getServletContext().getInitParameter("captcha");
        CaptchaFactory.setOption(captchaMode);
        Timer checker = new Timer();
        checker.schedule(new CaptchaTimeChecker(servletContextEvent), 0, 1000 * 60);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
