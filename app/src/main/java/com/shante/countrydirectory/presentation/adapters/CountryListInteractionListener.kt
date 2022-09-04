package com.shante.countrydirectory.presentation.adapters

import com.shante.countrydirectory.domain.Country


interface CountryListInteractionListener {

    fun onCountryCardClicked(country: Country)

}