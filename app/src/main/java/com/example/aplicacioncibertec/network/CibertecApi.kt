package com.example.aplicacioncibertec.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface CibertecApi {

    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

}