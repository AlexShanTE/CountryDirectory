package com.shante.countrydirectory.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
    val flags: Flag,
    val capital: String?,
    val alpha2Code: String?,
    val region: String,
    val subregion: String,
    val population: Long,
    val area: Double,
    val languages: List<Language>
) : Parcelable

@Parcelize
data class Flag(
    val png: String,
    val svg: String
) : Parcelable

@Parcelize
data class Language(
    val name: String
) : Parcelable
