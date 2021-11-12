package com.example.profilecardlayout.domain

import com.example.profilecardlayout.data.XingService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ReposModule {

    @Binds
    abstract fun bindGetRepos(getRepos: GetReposRepository) : XingService

}