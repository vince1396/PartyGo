package com.rpifinal.hitema.controller

import android.content.Intent
import android.view.View
import api.UserHelper.updateIsConnected
import butterknife.OnClick
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnSuccessListener
import com.rpifinal.hitema.R
import com.rpifinal.hitema.model.User

class MainMenu : BaseActivity() {
    private val mUser: User? = null
    override val fragmentLayout: Int
        get() = R.layout.activity_main_menu

    @OnClick(R.id.main_activity_btn_profil, R.id.play_text, R.id.profile_activity_logout_button)
    fun onClickMenu(view: View) {
        var activity = Intent()
        when (view.id) {
            R.id.main_activity_btn_profil -> activity = Intent(this@MainMenu, ProfileActivity::class.java)
            R.id.play_text -> activity = Intent(this@MainMenu, ConnectedUsersList::class.java)
        }
        startActivity(activity)
    }

    @OnClick(R.id.profile_activity_logout_button)
    fun onClickLogoutButton() {
        updateIsConnected("false", currentUser!!.uid).addOnSuccessListener { signOutUserFromFirebase() }
    }

    // Méthode de déconnexion (FirebaseAuth)
    private fun signOutUserFromFirebase() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, updateUIAfterRESTRequestsCompleted())
    }

    private fun updateUIAfterRESTRequestsCompleted(): OnSuccessListener<Void> {
        return OnSuccessListener {
            when (SIGN_OUT_TASK) {
                SIGN_OUT_TASK -> finish()
                else -> {
                }
            }
        }
    }

    companion object {
        private const val SIGN_OUT_TASK = 11
    }
}