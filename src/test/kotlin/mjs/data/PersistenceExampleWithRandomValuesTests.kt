package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mjs.helpers.randomStreet
import mjs.helpers.randomId
import mjs.helpers.randomPostcode
import mjs.helpers.randomState
import mjs.helpers.randomSuburb
import mjs.kotest.description

class PersistenceExampleWithRandomValuesTests : DescribeSpec({
    description(
        "Example tests of persisting values to the simple database, using randomly-generated values for the fields."
    )
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
            val address = Address(
                id = id,
                street = randomStreet(),
                suburb = suburb,
                state = state,
                postcode = postcode,
            )
            persistence.saveAddress(address)

            val newLine2 = randomStreet()
            persistence.saveAddress(address.copy(street = newLine2))

            persistence.getAddress(id)?.street shouldBe newLine2
        }
    }
})
