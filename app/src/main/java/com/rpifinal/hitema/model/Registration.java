package com.rpifinal.hitema.model;

public class Registration {

    private String uid;
    private String token;

    public Registration() {}

    public Registration(String uid, String token) {

        this.uid = uid;
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
