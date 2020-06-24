package com.rpifinal.hitema.partyGo.data.user.repositories

import androidx.lifecycle.MutableLiveData
import com.rpifinal.hitema.partyGo.data.user.model.User

interface IgetUserCallback {
    fun onCallback(value: MutableLiveData<User>)
}