package mjs.helpers

import mjs.data.states
import kotlin.random.Random

fun randomAddressLine(): String = "${words.random()} ${words.random()} ${words.random()}"

fun randomSuburb(): String = words.random()

fun randomState(): String = states.random()

fun randomPostcode(): Int = Random.nextInt(2000, 8000)
