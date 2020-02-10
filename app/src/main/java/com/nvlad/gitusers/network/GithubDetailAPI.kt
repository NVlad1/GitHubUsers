package com.nvlad.gitusers.network

import com.nvlad.gitusers.model.GithubUser
import com.nvlad.gitusers.model.GithubUserDetail
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import okhttp3.ResponseBody
import retrofit2.http.Path


interface GithubDetailAPI {

    @GET("users/{user}")
    fun getUserDetail(@Path("user") user: String): Call<GithubUserDetail>

    companion object {
        val ENDPOINT = "https://api.github.com"
    }
}