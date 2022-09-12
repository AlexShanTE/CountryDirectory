package com.shante.countrydirectory.data

import com.shante.countrydirectory.domain.Country
import com.shante.countrydirectory.domain.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countriesApi: RestCountriesApi
): CountryRepository {

    override suspend fun get(): List<Country> {
        return countriesApi.getCountryList()
    }

}

