package com.nvlad.gitusers.ui.userslist

import androidx.fragment.app.Fragment
import com.nvlad.gitusers.ui.SingleFragmentActivity

class UsersListActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return UsersListFragment()
    }
}