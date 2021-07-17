package com.suitmedia.core.data.source.remote

import com.suitmedia.core.data.source.remote.network.ApiResponse
import com.suitmedia.core.data.source.remote.network.ApiService
import com.suitmedia.core.data.source.remote.response.GuestResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    val getGuests: Flow<ApiResponse<List<GuestResponse>>> = flow {
        val dataArray = apiService.getGuest()
        if (dataArray.isNotEmpty()) {
            emit(ApiResponse.Success(dataArray))
        } else {
            emit(ApiResponse.Error("Empty"))
        }
    }.catch { e ->
        emit(ApiResponse.Error(e.toString()))
    }.flowOn(Dispatchers.IO)
}