package com.rpifinal.hitema.partyGo.data.dataSources

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rpifinal.hitema.partyGo.data.model.LoggedInUser
import javax.inject.Inject


class LoginDataSource @Inject constructor():FragmentActivity() {
    // =============================================================================================
    // ////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
    // =============================================================================================
    private val TAG = "LoginDataSource";

    private var RC_SIGN_IN = 123

    // Choose authentication providers
    private val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
    )

    var firebaseUser: FirebaseUser? = null
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
    fun login() {
        // Create and launch sign-in intent
        Log.d(TAG, "login : Create and launch sign-in intent")
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN)
    }
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
    fun logout() {
        // TODO : Logout
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    // TODO : Handle logout
                }
    }
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d(TAG, "onActivityResult")
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "Getting response")
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                Log.d(TAG, "Successfully signed in")
                firebaseUser = FirebaseAuth.getInstance().currentUser
            } else {
                if (response == null) {
                    Log.w(TAG, "Login Canceled")
                } else {
                    Log.w(TAG, "Error login")
                    response.error?.errorCode
                }
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
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