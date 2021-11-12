package com.example.profilecardlayout.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideXingApi() : XingApi {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create()) // convert the Json into the object #RepositoryResponse
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // converts the data into an Observable variable
            .build()
            .create(XingApi::class.java)
    }
}