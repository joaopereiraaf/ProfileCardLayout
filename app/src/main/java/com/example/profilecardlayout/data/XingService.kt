package com.example.profilecardlayout.data

import com.example.profilecardlayout.domain.GetReposRepository
import com.example.profilecardlayout.domain.RepositoryModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class XingService @Inject constructor(private val api: XingApi) : GetReposRepository {

    override fun getRepos(): Single<List<RepositoryModel>> {
        return api.getRepos().map { it.toDomainModel() }
    }
}