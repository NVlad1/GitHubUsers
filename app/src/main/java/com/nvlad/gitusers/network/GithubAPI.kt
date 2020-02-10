package com.nvlad.gitusers.network

import com.nvlad.gitusers.model.GithubUser
import com.nvlad.gitusers.model.GithubUserDetail
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import okhttp3.ResponseBody
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubAPI {

    @GET("users")
    fun getUsers(): Call<List<GithubUser>>

    @GET("users")
    fun getUsers(@Query("since") since: Int): Call<List<GithubUser>>

    companion object {
        val ENDPOINT = "https://api.github.com"
    }
}