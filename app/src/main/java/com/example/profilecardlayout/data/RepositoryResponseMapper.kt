package com.example.profilecardlayout.data

import com.example.profilecardlayout.domain.RepositoryModel

fun RepositoryResponse.toDomainModel() : RepositoryModel {
    return RepositoryModel(
        repositoryName = name,
        repositoryFullName = fullName,
        private = private,
        ownerName = owner.login
    )
}

fun List<RepositoryResponse>.toDomainModel() : List<RepositoryModel> {
    return this.map { it.toDomainModel() }
}