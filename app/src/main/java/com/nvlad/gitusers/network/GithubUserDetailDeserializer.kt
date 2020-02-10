package com.nvlad.gitusers.network

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.nvlad.gitusers.model.GithubUserDetail
import java.lang.reflect.Type

class GithubUserDetailDeserializer : JsonDeserializer<GithubUserDetail> {
    val TAG = GithubUserDetailDeserializer::class.java.name

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): GithubUserDetail {
        val userJsonObject = json.asJsonObject
        Log.d(TAG, userJsonObject.toString())
        val login = userJsonObject.get("login").asString
        val avatarURL = userJsonObject.get("avatar_url").asString
        val htmlURL = userJsonObject.get("html_url").asString
        val publicRepos = userJsonObject.get("public_repos").asInt
        val publicGists = userJsonObject.get("public_gists").asInt
        val followers = userJsonObject.get("followers").asInt
        val githubUserDetail = GithubUserDetail(login, avatarURL, htmlURL, publicRepos, publicGists, followers)
        return githubUserDetail
    }
}