package com.example.aplicacioncibertec.network

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("token")
    val token: String
)