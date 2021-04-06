package com.example.epi.model;

public class Users {
    private Integer userid;

    private String username;

    private String temperature;

    private String clas;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas == null ? null : clas.trim();
    }
}