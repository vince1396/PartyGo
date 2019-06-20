package com.rpifinal.hitema.model;

import androidx.annotation.Nullable;

public class User {

    private String mUid;
    private String mEmail;
    private String mUsername;
    private String mFirstName;
    private String mLastName;
    @Nullable private String mUrlPicture;
    private int mLvl;
    private int mXp;
    private boolean mIsConnected;


    // =============================================================================================
    // Void constructor
    public User(){}

    // Parameters constructor
    public User(String uid, String email, String username, String firstName, String lastName,
                @Nullable String urlPicture, int lvl, int xp, boolean isConnected) {

        this.mUid = uid;
        this.mEmail = email;
        this.mUsername = username;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mUrlPicture = urlPicture;
        this.mLvl = lvl;
        this.mXp = xp;
        this.mIsConnected = isConnected;
    }
    // =============================================================================================
    // --- GETTERS ---
    public String getUid() { return mUid; }

    public String getEmail() {
        return mEmail;
    }

    public String getUsername() { return mUsername; }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public int getLvl() {
        return mLvl;
    }

    public int getXp() {
        return mXp;
    }

    @Nullable
    public String getUrlPicture() { return mUrlPicture; }
    // =============================================================================================
    // --- SETTERS ---
    public void setUid(String mUid) { this.mUid = mUid; }

    public void setEmail(String mEmail) { this.mEmail = mEmail; }

    public void setUsername(String mUsername) { this.mUsername = mUsername; }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public void setLastName(String lastName) { this.mLastName = lastName; }

    public void setUrlPicture(@Nullable String mUrlPicture) { this.mUrlPicture = mUrlPicture; }

    public void setLvl(int mLvl) { this.mLvl = mLvl; }

    public void setXp(int xp) {
        this.mXp = xp;
    }
}