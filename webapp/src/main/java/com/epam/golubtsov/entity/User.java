package com.epam.golubtsov.entity;

public class User {

    private String name;
    private String email;
    private String password;
    private String rep_password;
    private String icon;

    public User() {
    }

    public User(String name, String email, String password, String rep_password, String icon) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rep_password = rep_password;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
