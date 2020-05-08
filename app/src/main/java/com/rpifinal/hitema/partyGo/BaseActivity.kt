package com.rpifinal.hitema.partyGo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

abstract class BaseActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    protected fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

    protected fun isCurrentUserLogged(): Boolean = getCurrentUser() != null
}