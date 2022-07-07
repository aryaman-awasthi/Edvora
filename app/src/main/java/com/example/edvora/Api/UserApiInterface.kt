package com.example.edvora.Api

import com.example.edvora.ModelClasses.DataModel
import com.example.edvora.ModelClasses.User
import retrofit2.Call
import retrofit2.http.GET

interface UserApiInterface {
    @GET("user")
    fun getData(): Call<User>
}