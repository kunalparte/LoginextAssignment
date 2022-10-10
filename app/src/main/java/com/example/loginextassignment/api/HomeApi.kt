package com.example.loginextassignment.api

import com.example.loginextassignment.model.HomeModel
import io.reactivex.Flowable
import retrofit2.http.GET


interface HomeApi {

    @GET("v1/0c5d380f-972a-44c9-bd11-ca5a2f154019")
    fun gwtData(): Flowable<HomeModel>
}