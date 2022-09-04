package com.shante.countrydirectory.domain.repository

import androidx.lifecycle.LiveData
import com.shante.countrydirectory.domain.Country

interface CountryListRepository {

    suspend fun getCountryByName(countryName: String): Country

    suspend fun getCountryList(): List<Country>

}