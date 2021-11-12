package com.example.profilecardlayout.domain

data class RepositoryModel(
    val repositoryName: String,
    val repositoryFullName: String,
    val private: Boolean,
    val ownerName: String
    )