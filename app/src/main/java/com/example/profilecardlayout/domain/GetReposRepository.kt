package com.example.profilecardlayout.domain

import io.reactivex.rxjava3.core.Single

interface GetReposRepository {

    fun getRepos() : Single<List<RepositoryModel>>

}