package mjs.helpers

import kotlin.random.Random

fun randomId(): String = Random.nextLong(0, 1_000_000_000_000).toString()
