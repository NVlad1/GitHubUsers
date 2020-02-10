package com.nvlad.gitusers.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nvlad.gitusers.model.GithubUserDetail
import com.nvlad.gitusers.network.UsersRepository

class UserViewModel: ViewModel() {
    val repository: UsersRepository
    lateinit var user: LiveData<GithubUserDetail>

    init{
        repository = UsersRepository.instance
    }

    fun fetchUser(login: String): LiveData<GithubUserDetail>{
        user = repository.getUserDetail(login)
        return user
    }
}