package com.rpifinal.hitema.partyGo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rpifinal.hitema.R
import com.rpifinal.hitema.databinding.FragmentHeaderBinding
import com.rpifinal.hitema.partyGo.dagger.DaggerAppComponent
import com.rpifinal.hitema.partyGo.data.user.viewModel.UserViewModel
import javax.inject.Inject

class HeaderFragment : Fragment() {

    private val TAG = "HeaderFragment"
    private lateinit var binding: FragmentHeaderBinding
    @Inject lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHeaderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}