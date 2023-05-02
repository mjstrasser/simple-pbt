package mjs.helpers

import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.localDate
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.string
import io.kotest.property.exhaustive.enum
import mjs.data.State
import java.time.LocalDate

/*
 * Kotest property generators for types in the database.
 */
val genId = Arb.long(1_000_000, 1_000_000_000_000)
val genName = Arb.string(1, 60)
val genDob = Arb.localDate(LocalDate.of(1870, 1, 1), LocalDate.now())
val genAddressLine = Arb.string(2, 100)
val genSuburb = Arb.string(2, 50)
val genState = Exhaustive.enum<State>()
val genPostcode = Arb.int(1000, 9000)