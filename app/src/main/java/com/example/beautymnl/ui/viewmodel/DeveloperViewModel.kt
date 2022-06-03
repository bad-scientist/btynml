package com.example.beautymnl.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beautymnl.data.repository.DeveloperRepository
import com.example.beautymnl.data.model.Developer
import com.example.beautymnl.data.model.Resource
import com.example.beautymnl.data.model.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class DeveloperViewModel constructor(var developerRepository: DeveloperRepository) : ViewModel() {

    val developer = MutableLiveData<Resource<Developer>>()
    val message = MutableLiveData<Resource<String>>()

    private val developers = MutableLiveData<Resource<ArrayList<Developer>>>()
    private val compositeDisposable = CompositeDisposable()

    fun init () {
        message.postValue(Resource.loading(null))
        developer.postValue(Resource.loading(null))
    }

    fun fetchDevelopers() {
        developers.postValue(Resource.loading(null))
        compositeDisposable.add(
            developerRepository.getDevelopers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ developersList ->
                    developers.postValue(Resource.success(developersList.body()))
                }, { throwable ->
                    developers.postValue(Resource.error("Error", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getDevelopers(): MutableLiveData<Resource<ArrayList<Developer>>> {
        return developers
    }

    fun addDeveloper(dev: Developer) {

        developer.postValue(Resource.loading(null))
        compositeDisposable.add(
            developerRepository.addDeveloper(dev)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dev ->
                    developer.postValue(Resource.success(dev.body()))
                }, { throwable ->
                    developers.postValue(Resource.error("Error", null))
                })
        )
    }

    fun editDeveloper(data: Developer) {
        developer.postValue(Resource.loading(null))
        compositeDisposable.add(
            developerRepository.updateDeveloper(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dev ->
                    developer.postValue(Resource.success(dev.body()))
                }, { throwable ->
                    developers.postValue(Resource.error("Error", null))
                })
        )
    }

    fun deleteDeveloper(id: Int) {
        message.postValue(Resource.loading(null))
        compositeDisposable.add(
            developerRepository.deleteDeveloper(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    message.postValue(Resource.success(result.body()))
                }, { throwable ->
                    message.postValue(Resource.loading(null))
                })
        )
    }
}