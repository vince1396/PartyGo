package com.rpifinal.hitema.partyGo.data.dataSources

import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.rpifinal.hitema.partyGo.data.model.LoggedInUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataSource @Inject constructor() {
    // =============================================================================================
    // ////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
    // =============================================================================================
    private val TAG = "LoginDataSource";
    var firebaseUser: FirebaseUser? = null
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
    fun login() {
        // Create and launch sign-in intent
        Log.d(TAG, "login : Create and launch sign-in intent")
    }
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
    fun getLoggedInUser(): LoggedInUser {
        Log.d(TAG, "getLoggedInUser")
        return LoggedInUser(
                firebaseUser!!.uid,
                firebaseUser?.displayName,
                firebaseUser?.email,
                firebaseUser?.phoneNumber,
                firebaseUser?.photoUrl
        )
    }
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
}