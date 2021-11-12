package com.example.profilecardlayout.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface XingApi {
    @GET("orgs/xing/repos")
    fun getRepos() : Single<List<RepositoryResponse>> // Single is an Observable, emits variable and then closes

    @GET("repos/xing/{name}")
    fun getRepo(@Path("name") name : String) : Single<List<RepositoryResponse>>
}