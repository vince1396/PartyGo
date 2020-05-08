package com.rpifinal.hitema.partyGo.data.repositories

import android.util.Log
import com.rpifinal.hitema.partyGo.data.model.LoggedInUser
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that requests  user information from the remote data source and
 * maintains an in-memory cache of user credentials information.
 */
@Singleton
class UserRepository @Inject constructor()
{
    private val TAG = "LoginRepository"
    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    init {
        Log.d(TAG, "init")
        user = null
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        Log.d(TAG, "setLoggedInUser")
        this.user = loggedInUser
    }
}
