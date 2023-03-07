package com.example.excahngeratedemo.data.models


import com.google.gson.annotations.SerializedName

data class Rates(
    @SerializedName("USD")
    val uSD: Double,
    @SerializedName("AED")
    val aED: Double,
    @SerializedName("ARS")
    val aRS: Double,
    @SerializedName("AUD")
    val aUD: Double,
    @SerializedName("CAD")
    val cAD: Double,
)