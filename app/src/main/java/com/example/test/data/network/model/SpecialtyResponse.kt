package com.example.test.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpecialtyResponse(
    @SerialName("name")
    val name: String,
    @SerialName("specialty_id")
    val specialtyId: Int
)