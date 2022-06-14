package com.example.test.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse(
    @SerialName("response")
    val response: List<Response>
)