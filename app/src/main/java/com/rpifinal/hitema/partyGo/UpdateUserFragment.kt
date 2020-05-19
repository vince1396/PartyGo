package com.rpifinal.hitema.partyGo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rpifinal.hitema.R
import com.rpifinal.hitema.databinding.FragmentUpdateUserBinding
import com.rpifinal.hitema.partyGo.dagger.AppComponent
import com.rpifinal.hitema.partyGo.dagger.DaggerAppComponent
import com.rpifinal.hitema.partyGo.data.user.viewModel.UserViewModel
import javax.inject.Inject

class UpdateUserFragment : Fragment() {

    private val TAG = "UpdateUserFragment"
    private lateinit var binding: FragmentUpdateUserBinding
    @Inject lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
        Log.d(TAG, "Update user fragment created")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_user, container, false)
    }
}