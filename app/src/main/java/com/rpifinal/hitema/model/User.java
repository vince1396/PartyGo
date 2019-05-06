package com.rpifinal.hitema.model;
import androidx.annotation.Nullable;

public class User {

    private String mUid;
    private String mUsername;
    private String mEmail;
    private int mLvl;
    @Nullable private String mUrlPicture;

    // =============================================================================================
    // Void constructor
    public User(){}

    // Parameters constructor
    public User(String uid, String email, String username, @Nullable String urlPicture, int lvl) {
        this.mUid = uid;
        this.mUsername = username;
        this.mEmail = email;
        this.mLvl = lvl;
        this.mUrlPicture = urlPicture;
    }

    // =============================================================================================
    // --- GETTERS ---
    public String getUid() {

        return mUid;
    }
    public String getUsername() {

        return mUsername;
    }
    public String getEmail() {
        return mEmail;
    }
    public int getLvl() {
        return mLvl;
    }

    @Nullable
    public String getUrlPicture() {

        return mUrlPicture;
    }
    // =============================================================================================
    // --- SETTERS ---
    public void setUsername(String mUsername) {

        this.mUsername = mUsername;
    }
    public void setUid(String mUid) {

        this.mUid = mUid;
    }
    public void setUrlPicture(@Nullable String mUrlPicture) {

        this.mUrlPicture = mUrlPicture;
    }
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }
    public void setLvl(int mLvl) {
        this.mLvl = mLvl;
    }
}