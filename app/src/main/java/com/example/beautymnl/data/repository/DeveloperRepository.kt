package com.example.beautymnl.data.repository

import com.example.beautymnl.data.api.DeveloperService
import com.example.beautymnl.data.model.Developer
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

class DeveloperRepository constructor(private val service: DeveloperService) {

    fun getDevelopers(): Single<Response<ArrayList<Developer>>> {
        return service.getDevelopers()
    }

    fun getDeveloper(id: String): Single<Response<Developer>> {
        return service.getDeveloper(id)
    }

    fun addDeveloper(developer: Developer): Single<Response<Developer>> {
        return service.addDeveloper(developer)
    }

    fun updateDeveloper(developer: Developer): Single<Response<Developer>> {
        return service.updateDeveloper(developer)
    }

    fun deleteDeveloper(id: Int): Single<Response<String>> {
        return service.deleteDeveloper(id)
    }
}