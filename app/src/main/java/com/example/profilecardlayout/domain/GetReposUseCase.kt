package com.example.profilecardlayout.domain

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetReposUseCase @Inject constructor(private val repos: GetReposRepository) {

    operator fun invoke() : Single<List<RepositoryModel>> = repos.getRepos()

}