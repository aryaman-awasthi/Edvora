package com.example.edvora.Api

import com.example.edvora.ModelClasses.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface RideApiInterface {
    @GET("rides")
    fun getData(): Call<List<DataModel>>
}