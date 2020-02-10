package com.nvlad.gitusers.model

data class GithubUserDetail(val login: String, val avatarURL: String, val htmlURL: String,
                            val publicRepos: Int, val publicGists: Int, val followers: Int)