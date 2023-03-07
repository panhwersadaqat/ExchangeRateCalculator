package com.example.excahngeratedemo.data.models

import com.google.gson.annotations.SerializedName

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
    @SerializedName("time_last_update_unix")
    val time_last_update_unix: Int,
    @SerializedName("time_next_update_unix")
    val time_next_update_unix: String,
    @SerializedName("time_next_update_utc")
    val time_next_update_utc: Int,
    @SerializedName("time_eol_unix")
    val time_eol_unix: Int,
    @SerializedName("base_code")
    val base_code: String,
    @SerializedName("rates")
    val rates: Rates,
    @SerializedName("success")
    val success: Boolean,
)