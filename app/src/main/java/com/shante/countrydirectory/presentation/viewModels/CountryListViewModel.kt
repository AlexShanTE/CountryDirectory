package com.shante.countrydirectory.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shante.countrydirectory.data.RemoteRepositoryImpl
import com.shante.countrydirectory.domain.Country
import com.shante.countrydirectory.presentation.adapters.CountryListInteractionListener
import com.shante.countrydirectory.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryListViewModel : ViewModel(), CountryListInteractionListener {

    private val repository = RemoteRepositoryImpl

    var data: List<Country> = emptyList()
    val countryList = MutableLiveData<List<Country>>()

    val navigateToCountryDetailsScreen = SingleLiveEvent<Country>()

    fun getAllCountries() {
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
        if (filter == "") {
            countryList.value = data
        } else {
            countryList.value = data.filter { it.name.contains(filter, ignoreCase = true) }
        }
    }

    override fun onCountryCardClicked(country: Country) {
        navigateToCountryDetailsScreen.value = country
    }


}