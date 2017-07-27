package com.epam.golubtsov.web.captcha;

import com.epam.golubtsov.entity.Captcha;

import javax.servlet.ServletContextEvent;
import java.time.LocalTime;
import java.util.Enumeration;
import java.util.TimerTask;

import static java.time.temporal.ChronoUnit.MINUTES;

public class CaptchaTimeChecker extends TimerTask {

    ServletContextEvent servletContextEvent;

    public CaptchaTimeChecker(ServletContextEvent servletContextEvent) {
        this.servletContextEvent = servletContextEvent;
    }

    @Override
    public void run() {
        Enumeration attributeNames = servletContextEvent.getServletContext().getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String current = (String) attributeNames.nextElement();
            if (servletContextEvent.getServletContext().getAttribute(current) instanceof Captcha) {
                Captcha captcha = (Captcha) servletContextEvent.getServletContext().getAttribute(current);
                if (MINUTES.between(captcha.getBorn(), LocalTime.now()) >= captcha.getWaitingTime()) {
                    servletContextEvent.getServletContext().removeAttribute(current);
                }
            }
        }
    }
}