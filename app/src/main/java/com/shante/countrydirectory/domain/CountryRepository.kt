package com.shante.countrydirectory.domain


interface CountryRepository {

    suspend fun get(): List<Country>

}