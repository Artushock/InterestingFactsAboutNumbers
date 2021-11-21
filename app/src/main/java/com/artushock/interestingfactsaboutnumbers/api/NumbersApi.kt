package com.artushock.interestingfactsaboutnumbers.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("{number}")
    fun getSpecificNumberFact(
        @Path("number") number: String,
    ): Call<String>

    @GET("random/math")
    fun getRandomNumberFact(): Call<String>
}