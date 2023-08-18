package com.example.aplicacioncibertec.network

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UsersResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val per_page: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("data")
    val data: List<User>,
    @SerializedName("support")
    var support: Support
)

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("avatar")
    val avatar: String?
)


data class Support (
    @SerializedName("url"  ) var url  : String? = null,
    @SerializedName("text" ) var text : String? = null
)