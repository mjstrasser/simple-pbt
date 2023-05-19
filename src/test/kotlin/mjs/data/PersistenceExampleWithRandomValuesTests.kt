package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mjs.helpers.randomId
import mjs.helpers.randomPostcode
import mjs.helpers.randomState
import mjs.helpers.randomStreet
import mjs.helpers.randomSuburb
import mjs.kotest.description

class PersistenceExampleWithRandomValuesTests : DescribeSpec({
    description("""
        Example-based tests of persisting values to the simple database,
        using randomly-generated values for the fields.
    """.trimIndent())
    describe("persisting addresses") {
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
        it("updates an address, identified by ID") {
            val persistence = Persistence()
            val id = randomId()
            val suburb = randomSuburb()
            val state = randomState()
            val postcode = randomPostcode()

            val address1 = Address(id, randomStreet(), suburb, state, postcode)
            persistence.saveAddress(address1)

            val address2 = address1.copy(street = randomStreet())
            persistence.saveAddress(address2)

            persistence.getAddress(id) shouldBe address2
        }
    }
})
