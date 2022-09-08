package com.shante.countrydirectory.presentation.utils

import com.shante.countrydirectory.domain.Language

fun List<Language>.getFormattedString(): String {
    return this.joinToString(separator = ", ") { it.name }
}