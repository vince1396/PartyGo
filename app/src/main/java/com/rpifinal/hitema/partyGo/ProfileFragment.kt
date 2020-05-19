package com.rpifinal.hitema.partyGo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rpifinal.hitema.databinding.FragmentProfileBinding
import com.rpifinal.hitema.partyGo.dagger.AppComponent
import com.rpifinal.hitema.partyGo.dagger.DaggerAppComponent
import com.rpifinal.hitema.partyGo.data.user.model.User
import com.rpifinal.hitema.partyGo.data.user.viewModel.UserViewModel
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private val TAG = "ProfileFragment"
    private lateinit var binding: FragmentProfileBinding
    @Inject lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)

        Log.d(TAG, "Profile fragment created")

        viewModel.user.observe(this, Observer { user ->
            Log.d(TAG, user.toString())
            updateUserUI(user)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUserUI(user: User) {
        Log.d(TAG, "In updateUserUI")
        Log.d(TAG, "User: $user")
        binding.emailValue.text = user.email.toString()
        binding.phoneValue.text = user.phoneNumber.toString()
        binding.firstNameValue.text = user.firstName.toString()
        binding.lastNameValue.text = user.lastName.toString()
        binding.usernameValue.text = user.userName.toString()
        binding.lvlValue.text = user.lvl.toString()
        binding.xpValue.text = user.xp.toString()
    }
}
