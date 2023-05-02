package mjs.helpers

import mjs.data.State
import kotlin.random.Random

fun randomAddressLine(): String = "${words.random()} ${words.random()} ${words.random()}"

fun randomSuburb(): String = words.random()

fun randomState(): State = State.values().random()

fun randomPostcode(): Int = Random.nextInt(2000, 8000)
