package com.example.aplicacioncibertec

import com.example.aplicacioncibertec.network.ApiService
import com.example.aplicacioncibertec.network.LoginRequest
import com.example.aplicacioncibertec.network.LoginResponse
import io.reactivex.Single

class LoginRepository {

    private val api = ApiService().apiService

    fun login(email: String, pass: String): Single<LoginResponse> {
        return api.login(LoginRequest(email, pass))
    }

}