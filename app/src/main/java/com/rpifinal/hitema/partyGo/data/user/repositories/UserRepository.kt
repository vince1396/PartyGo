package com.rpifinal.hitema.partyGo.data.user.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.rpifinal.hitema.partyGo.data.user.model.User
import oldFiles.api.UserHelper
import javax.inject.Inject

/**
 * Class that requests  user information from the remote data source and
 * maintains an in-memory cache of user credentials information.
 */
class UserRepository @Inject constructor()
{
    private val TAG = "UserRepository"
    lateinit var requestedUser: LiveData<User>

    init {
        getUser(FirebaseAuth.getInstance().uid.toString())
    }

    private fun fetchUser(uid: String, callback: IgetUserCallback) {
        val user: MutableLiveData<User> = MutableLiveData()
        UserHelper.getUser(uid).addOnSuccessListener {
            Log.d(TAG, uid)
            Log.d(TAG, it.toString())
            if(it.exists()) {
                Log.d(TAG, "Document exists")
                user.value = it.toObject(User::class.java)
            } else {
                Log.d(TAG, "Document doesn't exist")
            }
        }
        callback.onCallback(user)
    }

    private fun getUser(uid: String) {
        fetchUser(uid, object: IgetUserCallback {
            override fun onCallback(value: MutableLiveData<User>) {
                requestedUser = value
            }
        })
    }
}
