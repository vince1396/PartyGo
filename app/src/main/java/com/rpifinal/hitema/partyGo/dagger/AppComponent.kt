package com.rpifinal.hitema.partyGo.dagger

import android.app.Application
import com.rpifinal.hitema.partyGo.NewUserFragment
import com.rpifinal.hitema.partyGo.PocActivity
import com.rpifinal.hitema.partyGo.ProfileFragment
import com.rpifinal.hitema.partyGo.data.user.repositories.UserRepository
import com.rpifinal.hitema.partyGo.data.user.viewModel.UserViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(pocActivity: PocActivity)
    fun inject(userViewModel: UserViewModel)
    fun inject(userRepository: UserRepository)
    fun inject(profileFragment: ProfileFragment)
    fun inject(newUserFragment: NewUserFragment)
}

// appComponent lives in the Application class to share its lifecycle
class PartyGo: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: AppComponent = DaggerAppComponent.create()
}
