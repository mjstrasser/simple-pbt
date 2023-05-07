package mjs.data

enum class State { ACT, NSW, VIC, QLD, SA, WA, TAS, NT }

data class Address(
    val id: Long,
    val line1: String,
    val line2: String? = null,
    val suburb: String,
    val state: State,
    val postcode: Int,
)
