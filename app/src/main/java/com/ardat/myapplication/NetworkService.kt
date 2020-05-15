package com.ardat.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("films")
    fun getListFilm(): Call<List<RespondsAPIItem>>

    @GET("films/{id}")
    fun getDetailFilm(
        @Path("id") id: String
    ): Call<RespondsAPIItem>
}