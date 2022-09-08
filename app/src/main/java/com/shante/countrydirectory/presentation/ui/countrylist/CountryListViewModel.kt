package com.shante.countrydirectory.presentation.ui.countrylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shante.countrydirectory.domain.Country
import com.shante.countrydirectory.domain.CountryRepository
import com.shante.countrydirectory.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

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
            val response = repository.get()
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

    fun onCountryCardClicked(country: Country) {
        navigateToCountryDetailsScreen.value = country
    }


}