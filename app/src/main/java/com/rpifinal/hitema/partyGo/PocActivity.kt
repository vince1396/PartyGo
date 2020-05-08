package com.rpifinal.hitema.partyGo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rpifinal.hitema.R
import com.rpifinal.hitema.partyGo.data.model.LoggedInUser
import com.rpifinal.hitema.partyGo.data.model.UserHelper

class PocActivity : BaseActivity() {

    private val TAG = "POC Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poc)

        Log.d(TAG, "POC started")
        testDeleteUser()
    }

    private fun testDeleteUser() {
        Log.d(TAG, "User deletion")
        UserHelper.deleteUser(getCurrentUser()!!.uid)
    }
}
