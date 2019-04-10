package com.rpifinal.hitema.model;
import android.support.annotation.Nullable;

import java.sql.Timestamp;


public class User {

    private String mUid;
    private String mUsername;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mLocation;
    private int mLvl;
    private Timestamp mBirthDate;
    private Timestamp mCreationDate;
    @Nullable private String mUrlPicture;

    // =============================================================================================
    // Void constructor
    public User(){}

    // Parameters constructor
    public User(String mUid, String mUsername, String mFirstName, String mLastName, String mEmail, String mLocation, int mLvl, Timestamp mBirthDate, Timestamp mCreationDate, @Nullable String mUrlPicture) {
        this.mUid = mUid;
        this.mUsername = mUsername;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mLocation = mLocation;
        this.mLvl = mLvl;
        this.mBirthDate = mBirthDate;
        this.mCreationDate = mCreationDate;
        this.mUrlPicture = mUrlPicture;
    }

    // =============================================================================================
    // --- GETTERS ---
    public String getUid() {

        return mUid;
    }
    public String getUsername() {

        return mUsername;
    }

    @Nullable
    public String getUrlPicture() {

        return mUrlPicture;
    }

    public String getFirstName() {
        return mFirstName;
    }
    public String getLastName() {
        return mLastName;
    }
    public String getLocation() {
        return mLocation;
    }
    public String getEmail() {
        return mEmail;
    }
    public int getLvl() {
        return mLvl;
    }
    public Timestamp getBirthDate() {
        return mBirthDate;
    }
    public Timestamp getCreationDate() {
        return mCreationDate;
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
    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }
    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }
    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }
    public void setLvl(int mLvl) {
        this.mLvl = mLvl;
    }
    public void setBirthDate(Timestamp mBirthDate) {
        this.mBirthDate = mBirthDate;
    }
    public void setCreationDate(Timestamp mCreationDate) {
        this.mCreationDate = mCreationDate;
    }
}