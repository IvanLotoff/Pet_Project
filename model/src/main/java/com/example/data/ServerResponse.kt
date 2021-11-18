package com.example.data

import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("per_page")
    val numberOfUsersPerPage: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val users: List<User>,
    @SerializedName("ad")
    val ad: Ad
)
