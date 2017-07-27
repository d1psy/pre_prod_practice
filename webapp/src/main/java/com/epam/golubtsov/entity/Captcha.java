package com.epam.golubtsov.entity;

import java.time.LocalTime;

public class Captcha {

    private String id;
    private String text;
    private LocalTime born;
    private int waitingMinutes;

    public Captcha(String id, String text, LocalTime born, int waitingMinutes) {
        this.id = id;
        this.text = text;
        this.born = born;
        this.waitingMinutes = waitingMinutes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalTime getBorn() {
        return born;
    }

    public int getWaitingTime() {
        return waitingMinutes;
    }
}