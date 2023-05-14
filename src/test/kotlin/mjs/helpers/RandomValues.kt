package mjs.helpers

import mjs.data.State
import kotlin.random.Random

fun randomId(): Long = Random.nextLong(1_000_000, 1_000_000_000_000)

fun randomAddressLine(): String = "${words.random()} ${words.random()} ${words.random()}"

fun randomSuburb(): String = words.random()

fun randomState(): State = State.values().random()

fun randomPostcode(): Int = Random.nextInt(2000, 9000)
