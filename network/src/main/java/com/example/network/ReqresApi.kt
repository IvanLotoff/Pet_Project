package com.example.network

import com.example.data.ServerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresApi {
    @GET("api/users")
    suspend fun fetchData(@Query("page") currentPageNumber: Int): Response<ServerResponse>
}