package com.example.test.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("avatr_url")
    val avatar: String?,
    @SerialName("birthday")
    val birthday: String?,
    @SerialName("f_name")
    val firstName: String?,
    @SerialName("l_name")
    val lastName: String?,
    @SerialName("specialty")
    val specialty: List<SpecialtyResponse>
)