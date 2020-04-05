package com.rpifinal.hitema.partyGo.data.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rpifinal.hitema.partyGo.data.repositories.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val loginRepository: LoginRepository): ViewModel() {

    private val TAG = "LoginViewModel"

    fun login() {
        Log.d(TAG, "login")
        // can be launched in a separate asynchronous job
        loginRepository.login()
    }
}