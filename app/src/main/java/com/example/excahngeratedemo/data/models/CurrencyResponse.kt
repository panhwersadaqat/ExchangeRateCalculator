package com.example.excahngeratedemo.data.models

import com.google.gson.annotations.SerializedName

/**
data class of currency data
*/

data class CurrencyResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("documentation")
    val documentation: String,
    @SerializedName("terms_of_use")
    val terms_of_use: String,
    @SerializedName("base_code")
    val base_code: String,
    @SerializedName("rates")
    val rates: Rates,
    @SerializedName("success")
    val success: Boolean,
)