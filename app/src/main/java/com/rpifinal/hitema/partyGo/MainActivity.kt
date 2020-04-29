package com.rpifinal.hitema.partyGo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import javax.inject.Inject

class MainActivity @Inject constructor(): BaseActivity() {
    // =============================================================================================
    // ////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
    // =============================================================================================
    private val TAG = "MainActivity"

    private var RC_SIGN_IN = 123

    // Choose authentication providers
    private val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
    )
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startLogin()
    }

    override fun getFragmentLayout(): Int {
        // This function shouldn't be implemented here
        return 0
    }
    // =============================================================================================
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // =============================================================================================
    private fun startLogin() {
        Log.d(TAG, "Starting login")
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d(TAG, "onActivityResult")
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "Getting response")
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                Log.d(TAG, "Successfully signed in")
                // TODO : Next activity
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
}
