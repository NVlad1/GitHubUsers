package com.nvlad.gitusers.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nvlad.gitusers.R
import com.nvlad.gitusers.ui.SingleFragmentActivity

class UserActivity: SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return UserFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}