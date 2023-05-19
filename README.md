# Simple property-based testing

A Kotlin and [Kotest](https://kotest.io) repo to illustrate simple property-based testing (PBT).

# System under test

Using a simple model of an address:

```kotlin
enum class State { ACT, NSW, VIC, QLD, SA, WA, TAS, NT }

data class Address(
    val id: Long,
    val street: String,
    val suburb: String,
    val state: State,
    val postcode: Int,
)
```

And a simple persistence class that enforces data constraints:

```kotlin
class Persistence {
    private val addresses = mutableMapOf<Long, Address>()

    fun saveAddress(address: Address) {
        assert(address.id in (1_000_000..1_000_000_000_000))
        assert(address.street.length in (2..60))
        assert(address.suburb.length in (2..30))
        assert(address.postcode in (1000..9000))

        addresses[address.id] = address
    }

    fun getAddress(id: Long): Address? = addresses[id]
}
```

# Testing styles

## Example-based tests

Here is an [example-based test](src/test/kotlin/mjs/data/PersistenceExampleTests.kt):

```kotlin
        it("gets an address that was saved, identified by ID") {
            val persistence = Persistence()
            val twBneId = 7654321L
            val address = Address(
                id = twBneId,
                street = "Level 19, 127 Creek Street",
                suburb = "Brisbane",
                state = State.QLD,
                postcode = 4000,
            )
            persistence.saveAddress(address)

            persistence.getAddress(twBneId) shouldBe address
        }
```

## Added randomness

Here is a test for the [same functionality with introduced
randomness](src/test/kotlin/mjs/data/PersistenceExampleWithRandomValuesTests.kt):

```kotlin
        it("gets an address that was saved, identified by ID") {
            val persistence = Persistence()
            val id = randomId()
            val address = Address(
                id = id,
                street = randomStreet(),
                suburb = randomSuburb(),
                state = randomState(),
                postcode = randomPostcode(),
            )
            persistence.saveAddress(address)

            persistence.getAddress(id) shouldBe address
        }
```

## Property-based tests

Here is a test for [the same functionality using property-based
testing](src/test/kotlin/mjs/data/PersistencePropertyTests.kt):

```kotlin
        it("gets an address that was saved, identified by ID") {
            val persistence = Persistence()
            checkAll(
                genId,
                genStreet,
                genSuburb,
                genState,
                genPostcode
            ) { id, street, suburb, state, postcode ->
                val address = Address(
                    id = id,
                    street = street,
                    suburb = suburb,
                    state = state,
                    postcode = postcode
                )
                persistence.saveAddress(address)

                persistence.getAddress(id) shouldBe address
            }
        }
```
This test uses [these Kotest generators](src/test/kotlin/mjs/helpers/Generators.kt):

```kotlin
val genId = Arb.long(1_000_000..1_000_000_000_000)
val genStreet = Arb.string(2..60, Codepoint.alphanumeric())
val genSuburb = Arb.string(2..30, Codepoint.alphanumeric())
val genState = Exhaustive.enum<State>()
val genPostcode = Arb.int(1000..9000)
```
