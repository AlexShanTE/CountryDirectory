package com.shante.countrydirectory.data

import com.shante.countrydirectory.domain.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApi {

    @GET("all")
    suspend fun getCountryList(): List<Country>



}




