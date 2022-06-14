package com.example.test.data.network

import com.example.test.data.network.model.NetworkResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface GitlabApi {

    @GET("testTask.json")
    fun getEmployees(): Observable<NetworkResponse>
}