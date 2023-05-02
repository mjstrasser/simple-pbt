package mjs.data

import java.time.LocalDate

enum class State { ACT, NSW, VIC, QLD, SA, WA, TAS, NT }

data class Address(
    val id: Long,
    val line1: String,
    val line2: String? = null,
    val suburb: String,
    val state: State,
    val postcode: Int,
)

data class Person(
    val id: Long,
    val name: String,
    val dateOfBirth: LocalDate,
)