package com.example.beautymnl.data.api

import com.example.beautymnl.data.model.Developer
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface DeveloperService {

    @GET("developers")
    fun getDevelopers(): Single<Response<ArrayList<Developer>>>

    @GET("developers")
    fun getDeveloper(@Query("id") id: String): Single<Response<Developer>>

    @POST("developers")
    fun addDeveloper(@Body developer: Developer): Single<Response<Developer>>

    @PUT("developers")
    fun updateDeveloper(@Body developer: Developer): Single<Response<Developer>>

    @DELETE("developers/{id}")
    fun deleteDeveloper(@Path("id") id: Int): Single<Response<String>>
}