package com.rpifinal.hitema.partyGo.daggerComponents

import android.app.Application
import com.rpifinal.hitema.partyGo.data.viewModel.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent{
    fun inject() : LoginViewModel
}

class PartyGo(): Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}