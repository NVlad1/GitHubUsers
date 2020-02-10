package com.nvlad.gitusers.network

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.nvlad.gitusers.model.GithubUser
import java.lang.reflect.Type

class GithubUserDeserializer : JsonDeserializer<GithubUser> {
    val TAG = GithubUserDeserializer::class.java.name

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): GithubUser? {
        if (typeOfT != GithubUser::class.javaObjectType) return null
        val userJsonObject = json.asJsonObject
        val id = userJsonObject.get("id").asInt
        val login = userJsonObject.get("login").asString
        val avatarURL =  userJsonObject.get("avatar_url").asString
        val githubUser = GithubUser(id, login, avatarURL)
        return githubUser
    }
}