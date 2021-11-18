package com.example.petproject.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.User
import com.example.network.ReqresApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val api: ReqresApi) {
    fun getResultStream(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                maxSize = 100
            ),
            pagingSourceFactory = { ApiPagingDataSource(api) }
        ).flow
    }
}
