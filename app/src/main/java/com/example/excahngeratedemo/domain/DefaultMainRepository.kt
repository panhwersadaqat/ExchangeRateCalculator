package com.example.excahngeratedemo.domain


import com.example.excahngeratedemo.data.CurrencyApi
import com.example.excahngeratedemo.data.models.CurrencyResponse
import com.example.excahngeratedemo.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {
    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occured")
        }
    }
}