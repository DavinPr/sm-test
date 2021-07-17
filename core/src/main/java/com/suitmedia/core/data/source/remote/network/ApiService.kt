package com.suitmedia.core.data.source.remote.network

import com.suitmedia.core.data.source.remote.response.GuestResponse
import retrofit2.http.GET

interface ApiService {
    @GET("596dec7f0f000023032b8017")
    suspend fun getGuest() : List<GuestResponse>
}