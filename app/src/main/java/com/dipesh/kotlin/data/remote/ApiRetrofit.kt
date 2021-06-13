package com.dipesh.kotlin.data.remote

import com.dipesh.kotlin.model.networkschemas.MembersListResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {
    @GET("/api/")
    fun getListOfMembersApi(@Query("results") resultSize: Int): Single<MembersListResult>
}