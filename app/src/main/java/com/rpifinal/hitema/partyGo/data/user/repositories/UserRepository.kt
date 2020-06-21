package com.rpifinal.hitema.partyGo.data.user.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rpifinal.hitema.partyGo.data.user.model.User
import com.rpifinal.hitema.partyGo.data.user.model.UserHelper
import kotlinx.coroutines.awaitAll
import javax.inject.Inject

/**
 * Class that requests  user information from the remote data source and
 * maintains an in-memory cache of user credentials information.
 */
class UserRepository @Inject constructor()
{
    private val TAG = "UserRepository"

    fun getUser( uid : String ) : LiveData<User> {
        val user: MutableLiveData<User> = MutableLiveData<User>()
        UserHelper.getUser(uid).addOnSuccessListener {
            Log.d(TAG, uid)
            Log.d(TAG, it.toString())
            if(it.exists()) {
                Log.d(TAG, "Document exists")
                Log.d(TAG, it.data.toString())
                user.value = it.toObject(User::class.java)
                Log.d(TAG, user.value.toString())
            } else {
                Log.d(TAG, "Document doesn't exist")
            }
        }
        Log.d(TAG, "User before return : " + user.value.toString())
        Log.d(TAG, user.toString())
        return user
    }
}