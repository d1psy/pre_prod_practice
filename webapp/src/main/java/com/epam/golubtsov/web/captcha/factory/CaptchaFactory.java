package com.epam.golubtsov.web.captcha.factory;

import com.epam.golubtsov.constant.CaptchaConstants;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CaptchaFactory {

    private static String option = CaptchaConstants.CONTEXT_CAPTCHA;

    public static void setOption(String option) {
        CaptchaFactory.option = option;
    }

    public static CaptchaManager get() {
        HashMap<String, CaptchaManager> options = obtainOption();
        if (options.get(option) == null) {
            throw new IllegalArgumentException("Wrong captcha manager");
        }
        return  options.get(option);
    }

    private static HashMap<String, CaptchaManager> obtainOption() {
        HashMap<String, CaptchaManager> options = new LinkedHashMap<>();
        options.put(CaptchaConstants.CONTEXT_CAPTCHA, new ContextCaptcha());
        options.put(CaptchaConstants.SESSION_CAPTCHA, new SessionCaptcha());
        return options;
    }
}
