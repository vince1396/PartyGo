package com.rpifinal.hitema.partyGo

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.rpifinal.hitema.R
import com.rpifinal.hitema.databinding.ActivityPocBinding
import com.rpifinal.hitema.partyGo.data.user.viewModel.UserViewModel
import javax.inject.Inject

class PocActivity : BaseActivity() {

    private val TAG = "POCActivity"
    private lateinit var binding: ActivityPocBinding
    @Inject lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poc)
        if(savedInstanceState == null)
            showFragment()

    }

    private fun showFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, MainMenuFragment())
            .commitAllowingStateLoss()
        println("AddedFragment")
    }
}
