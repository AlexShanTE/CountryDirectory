package com.shante.countrydirectory.data

import com.shante.countrydirectory.domain.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApi {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") countryName: String): Country
    @GET("all")
    suspend fun getCountryList(): List<Country>

    companion object {

        private const val BASE_URL = "https://restcountries.com/v2/"

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val restCountriesApi: RestCountriesApi = retrofit.create(RestCountriesApi::class.java)
    }


}




