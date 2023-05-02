package mjs.helpers

import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random
import kotlin.random.nextInt

fun instantNow(): Instant = Instant.now().truncatedTo(ChronoUnit.MICROS)

fun randomPastInstant(maxYears: Long = 1): Instant = instantNow()
    .minusSeconds(randomSecondsForYears(maxYears))

private fun randomSecondsForYears(maxYears: Long): Long =
    Random.nextLong(maxYears * 365 * 86_400)


fun randomPastDate(maxYears: Long = 100): LocalDate = LocalDate.now()
    .minusDays(Random.nextLong(maxYears * 365))
