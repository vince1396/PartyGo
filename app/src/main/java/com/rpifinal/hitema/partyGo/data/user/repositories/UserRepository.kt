package com.rpifinal.hitema.partyGo.data.user.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rpifinal.hitema.partyGo.data.user.model.User
import kotlinx.coroutines.awaitAll
import oldFiles.api.UserHelper
import javax.inject.Inject

/**
 * Class that requests  user information from the remote data source and
 * maintains an in-memory cache of user credentials information.
 */
class UserRepository @Inject constructor()
{
    private val TAG = "UserRepository"
    private val user: MutableLiveData<User> = MutableLiveData<User>()

    fun getUser(uid: String): LiveData<User> {
        if(getInfoUser(uid)){
            Log.d(TAG, "User before return : " + user.value.toString())
            Log.d(TAG, user.toString())
            return user
        }
        return user
    }

    fun getInfoUser( uid : String) : Boolean{
        return UserHelper.getUser(uid).addOnSuccessListener {
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
        }.isSuccessful()
    }
}