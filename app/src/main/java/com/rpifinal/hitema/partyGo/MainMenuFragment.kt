package com.rpifinal.hitema.partyGo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rpifinal.hitema.R
import com.rpifinal.hitema.databinding.FragmentMainMenuBinding
import com.rpifinal.hitema.partyGo.dagger.DaggerAppComponent
import com.rpifinal.hitema.partyGo.data.user.viewModel.UserViewModel
import javax.inject.Inject

class MainMenuFragment : Fragment() {

    private val TAG = "MainMenuFragment"
    private lateinit var binding: FragmentMainMenuBinding
    @Inject lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
