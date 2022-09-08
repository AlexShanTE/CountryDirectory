package com.shante.countrydirectory.di

import com.shante.countrydirectory.data.CountryRepositoryImpl
import com.shante.countrydirectory.data.RestCountriesApi
import com.shante.countrydirectory.domain.CountryRepository
import com.shante.countrydirectory.presentation.ui.countrylist.CountryListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCountryRepository(countriesApi: RestCountriesApi): CountryRepository {
        return CountryRepositoryImpl(countriesApi)
    }

}