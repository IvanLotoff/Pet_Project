package com.example.data

import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("text")
    val text: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("company")
    val company: String
)
