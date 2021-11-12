package com.example.profilecardlayout.data

import com.squareup.moshi.Json

data class RepositoryResponse (
    val id: Int,
    @field:Json(name = "node_id")
    val nodeId: String,
    val name: String,
    @field:Json(name = "full_name")
    val fullName: String,
    val private: Boolean,
    val owner: Owner
) {

    data class Owner (
        val login: String,
        @field:Json(name = "avatar_url")
        val avatarUrl: String
    )
}