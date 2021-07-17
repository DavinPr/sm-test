package com.suitmedia.core.data

import com.suitmedia.core.data.source.dummy.DummyDataSource
import com.suitmedia.core.data.source.remote.RemoteDataSource
import com.suitmedia.core.data.source.remote.network.ApiResponse
import com.suitmedia.core.domain.repository.IAppRepository
import com.suitmedia.core.domain.usecase.model.EventDomain
import com.suitmedia.core.domain.usecase.model.GuestDomain
import com.suitmedia.core.utils.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val dummyDataSource: DummyDataSource
) : IAppRepository {
    override fun getGuests(): Flow<Resource<List<GuestDomain>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getGuests.first()) {
            is ApiResponse.Success -> emit(
                Resource.Success(
                    apiResponse.data.mapIndexed { index, response -> response.toDomain(index) }
                )
            )
            is ApiResponse.Error -> emit(
                Resource.Error<List<GuestDomain>>(apiResponse.errorMessage)
            )
        }
    }

    override fun getEvents(): Flow<List<EventDomain>> = flow {
        emit(dummyDataSource.getEvents().map { it.toDomain() })
    }.flowOn(Dispatchers.IO)
}