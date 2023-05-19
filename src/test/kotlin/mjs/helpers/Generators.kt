package mjs.helpers

import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.Gen
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
val genId: Gen<Long> = Arb.long(1_000_000..1_000_000_000_000)
val genStreet: Gen<String> = Arb.string(2..60, Codepoint.alphanumeric())
val genSuburb: Gen<String> = Arb.string(2..30, Codepoint.alphanumeric())
val genState: Gen<State> = Exhaustive.enum<State>()
val genPostcode: Gen<Int> = Arb.int(1000..9000)
