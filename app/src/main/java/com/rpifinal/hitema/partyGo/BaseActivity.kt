package com.rpifinal.hitema.partyGo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

abstract class BaseActivity: AppCompatActivity() {
    // =============================================================================================
    // ////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
    // =============================================================================================

    /* Represents the user's session. Can be use to fetch user's connection infos,
       like checking if the user is logged in
     */
    private lateinit var auth: FirebaseAuth

    // =============================================================================================
    // //////////////////////////////////////// METHODS ////////////////////////////////////////////
    // =============================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fetching user's instance
        auth = FirebaseAuth.getInstance()
    }
    // =============================================================================================
    // Can be used to get current logged in user in subclasses
    protected fun getCurrentUser() = FirebaseAuth.getInstance().currentUser
    // =============================================================================================
    // Can be use in subclasses to check if a user is logged in
    protected fun isCurrentUserLogged(): Boolean = getCurrentUser() != null
    // =============================================================================================
    // /////////////////////////////////////////// END /////////////////////////////////////////////
    // =============================================================================================
}