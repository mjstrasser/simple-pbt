package mjs.helpers

import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.Codepoint
import io.kotest.property.arbitrary.alphanumeric
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.string
import io.kotest.property.exhaustive.enum
import mjs.data.State

/*
 * Kotest property generators for types in the database.
 */
val genId = Arb.long(1_000_000..1_000_000_000_000)
val genStreet = Arb.string(2..100, Codepoint.alphanumeric())
val genSuburb = Arb.string(2..50, Codepoint.alphanumeric())
val genState = Exhaustive.enum<State>()
val genPostcode = Arb.int(1000..9000)
