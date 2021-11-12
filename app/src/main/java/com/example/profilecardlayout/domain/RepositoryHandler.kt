package com.example.profilecardlayout.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profilecardlayout.data.XingApi
import com.example.profilecardlayout.data.XingService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

internal class RepositoryHandler @Inject constructor(
    private val getReposRepository: GetReposRepository
    ) : ViewModel() {

    private val disposable = CompositeDisposable()
    val repos = MutableLiveData<List<RepositoryModel>>()
    val repoLoadError = MutableLiveData<Boolean>()

//    fun refresh() {
//        fetchRepo()
//    }

    private fun fetchRepo() {
        disposable.add(
            getReposRepository.getRepos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<RepositoryModel>>() {
                    override fun onSuccess(value: List<RepositoryModel>) {
                        repos.value = value
                        repoLoadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        repoLoadError.value = true
                    }
                })
        )
    }
}