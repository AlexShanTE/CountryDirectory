package com.shante.countrydirectory.data

import com.shante.countrydirectory.data.RestCountriesApi.Companion.restCountriesApi
import com.shante.countrydirectory.domain.Country
import com.shante.countrydirectory.domain.repository.CountryListRepository

object RemoteRepositoryImpl : CountryListRepository {


    override suspend fun getCountryByName(countryName: String): Country {
        return restCountriesApi.getCountryByName(countryName)
    }

    override suspend fun getCountryList(): List<Country> {
        return restCountriesApi.getCountryList()
    }

}

