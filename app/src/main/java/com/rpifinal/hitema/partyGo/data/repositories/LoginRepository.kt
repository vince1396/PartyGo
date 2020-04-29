package com.rpifinal.hitema.partyGo.data.repositories

import android.util.Log
import com.rpifinal.hitema.partyGo.data.dataSources.LoginDataSource
import com.rpifinal.hitema.partyGo.data.model.LoggedInUser
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
@Singleton
class LoginRepository @Inject constructor(private val dataSource: LoginDataSource)
{
    private val TAG = "LoginRepository"
    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        Log.d(TAG, "init")
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        Log.d(TAG, "Logout")
        user = null
        //dataSource.logout()
    }

    fun login(): LoggedInUser {
        // handle login
        Log.d(TAG, "login")
        dataSource.login()
        setLoggedInUser(dataSource.getLoggedInUser())
        return dataSource.getLoggedInUser()
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        Log.d(TAG, "setLoggedInUser")
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
