package mjs.helpers

import kotlin.random.Random

fun randomId(): Long = Random.nextLong(1_000_000, 1_000_000_000_000)
