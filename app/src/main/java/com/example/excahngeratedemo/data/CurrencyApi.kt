package com.example.excahngeratedemo.data

import com.example.excahngeratedemo.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
interface for APIs
 */
interface CurrencyApi {
    @GET("latest/USD")
    suspend fun getRates(
        @Query("base") base: String,
    ) : Response<CurrencyResponse>
}