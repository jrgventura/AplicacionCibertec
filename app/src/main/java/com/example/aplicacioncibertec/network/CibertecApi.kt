package com.example.aplicacioncibertec.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CibertecApi {

    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("api/users") // https://reqres.in/api/users?page=2
    fun listUsers(@Query("page") page: Int): Single<UsersResponse>

    @GET("api/users/{id}") // https://reqres.in/api/users/2
    fun getUser(@Path("id") id: Int)


}