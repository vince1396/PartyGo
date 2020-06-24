package com.rpifinal.hitema.partyGo.data.user.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.rpifinal.hitema.partyGo.data.user.model.User
import com.rpifinal.hitema.partyGo.data.user.repositories.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(userRepository: UserRepository): ViewModel() {

    private val TAG = "UserViewModel"

    private val _user: LiveData<User>? = userRepository.requestedUser
    val user: LiveData<User>?
        get() = _user

    init {
        Log.d(TAG, "UserViewModel created !")
        Log.d(TAG, _user.toString())
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "UserViewModel destroyed !")
    }
}