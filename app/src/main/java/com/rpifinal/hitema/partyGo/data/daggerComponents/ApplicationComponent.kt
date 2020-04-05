package com.rpifinal.hitema.partyGo.data.daggerComponents

import Examples.data.LoginRepository
import android.app.Application
import com.rpifinal.hitema.partyGo.daggerComponents.DaggerApplicationComponent
import dagger.*
import javax.sql.DataSource

// Definition of the Application graph
@Component
interface ApplicationComponent{
    fun inject(dataSource: DataSource)
    fun inject(loginRepository: LoginRepository)
}

// appComponent lives in the Application class to share its lifecycle
class PartyGo:Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}