package com.nvlad.gitusers.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.nvlad.gitusers.network.GithubAPI
import com.nvlad.gitusers.model.GithubUser
import com.nvlad.gitusers.network.GithubUserDeserializer
import com.nvlad.gitusers.network.UsersRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UsersListViewModel: ViewModel() {
    val users: MediatorLiveData<MutableList<GithubUser>>
    val repository: UsersRepository
    var loadingNew = false

    init{
        repository = UsersRepository.instance
        users = MediatorLiveData()
        users.addSource(repository.getUsers()){ value ->
            if (value == null) {
                users.value = null
                return@addSource
            }
            users.value = ArrayList(value)
        }
    }

    fun updateUsers(){
        val updated = repository.getUsers()
        users.addSource(updated){value ->
            users.removeSource(updated)
            if (value == null) {
                if (users.value == null) users.value = null
                return@addSource
            }
            users.value = ArrayList(value)
            users.value = users.value
        }
    }

    fun fetchNew(){
        val usersArray = users.value ?: return
        val lastID = usersArray[usersArray.size-1].id
        loadSince(lastID)
    }

    private fun loadSince(since: Int){
        if (loadingNew) return
        loadingNew = true
        repository.getUsers(since){
            loadingNew = false
            if (it == null) return@getUsers
            users.value?.addAll(it)
            users.value = users.value
        }
    }

}