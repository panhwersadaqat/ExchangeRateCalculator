package com.example.excahngeratedemo.domain

import com.example.excahngeratedemo.data.models.CurrencyResponse
import com.example.excahngeratedemo.util.Resource

interface MainRepository {
    suspend fun getRates(base: String) : Resource<CurrencyResponse>
}