package mjs.data

enum class State { ACT, NSW, VIC, QLD, SA, WA, TAS, NT }

data class Address(
    val id: Long,
    val street: String,
    val suburb: String,
    val state: State,
    val postcode: Int,
)
