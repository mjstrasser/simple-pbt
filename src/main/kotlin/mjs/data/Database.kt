package mjs.data

import java.time.LocalDate

data class Address(
    val id: String,
    val line1: String,
    val line2: String? = null,
    val line3: String? = null,
    val suburb: String,
    val state: String,
    val postcode: Int,
)

data class Person(
    val id: String,
    val name: String,
    val dateOfBirth: LocalDate,
)