package com.example.petproject.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.User
import com.example.network.ReqresApi

class ApiPagingDataSource constructor(private val api: ReqresApi) :
    PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val position = params.key ?: 1
            val response = api.fetchData(position)
            val usersList = mutableListOf<User>()
            usersList.addAll(response.body()?.users ?: emptyList())
            val previousKey = if(position == 1) null else position - 1
            LoadResult.Page(
                data = usersList,
                prevKey = previousKey,
                nextKey = if (!usersList.isEmpty()) position.plus(1) else null
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}