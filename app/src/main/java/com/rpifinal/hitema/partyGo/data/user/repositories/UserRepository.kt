package com.rpifinal.hitema.partyGo.data.user.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getUser(uid: String): LiveData<User> {
        val user: MutableLiveData<User> = MutableLiveData<User>()
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
        return user
    }
}
