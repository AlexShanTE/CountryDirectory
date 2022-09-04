package com.shante.countrydirectory.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shante.countrydirectory.data.RemoteRepositoryImpl
import com.shante.countrydirectory.domain.Country
import com.shante.countrydirectory.presentation.adapters.CountryListInteractionListener
import com.shante.countrydirectory.utils.SingleLiveEvent
import kotlinx.coroutines.*

class CountryListViewModel   (
    application: Application
) : AndroidViewModel(application), CountryListInteractionListener {

    private val repository = RemoteRepositoryImpl

    private var data : List<Country> = emptyList()
    val countryList = MutableLiveData<List<Country>?>()

    val navigateToCountryDetailsScreen = SingleLiveEvent<Country>()

    fun getAllCountries() {
        if (countryList.value?.isNotEmpty() == true) return //means search request is not empty
        if (data.isNotEmpty()) {
            countryList.value = data
            return
        }
        viewModelScope.launch {
            Dispatchers.IO
            val response = repository.getCountryList()
            if (response.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    data = response
                    countryList.value = data
                }
            }
        }
    }

    fun getFilteredList(filter: String) {
        if (filter == "") getAllCountries() else {
            val newList = countryList.value?.filter {
                it.name.contains(filter, ignoreCase = true)
            }
            if (newList != null) {
                countryList.value = newList
            }
        }
    }


    override fun onCountryCardClicked(country: Country) {
        navigateToCountryDetailsScreen.value = country
    }


}