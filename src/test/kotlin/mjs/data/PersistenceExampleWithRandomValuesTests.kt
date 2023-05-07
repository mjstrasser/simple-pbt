package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mjs.helpers.randomAddressLine
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
        val persistence = Persistence()
        it("gets an address that was saved, identified by ID") {
            val id = randomId()
            val address = Address(
                id = id,
                line1 = randomAddressLine(),
                suburb = randomSuburb(),
                state = randomState(),
                postcode = randomPostcode(),
            )
            persistence.saveAddress(address)

            persistence.getAddress(id) shouldBe address
        }
        it("updates an address, identified by ID") {
            val id = randomId()
            val suburb = randomSuburb()
            val state = randomState()
            val postcode = randomPostcode()
            val address = Address(
                id = id,
                line1 = randomAddressLine(),
                line2 = randomAddressLine(),
                suburb = suburb,
                state = state,
                postcode = postcode,
            )
            persistence.saveAddress(address)

            val newLine2 = randomAddressLine()
            persistence.saveAddress(address.copy(line2 = newLine2))

            persistence.getAddress(id)?.line2 shouldBe newLine2
        }
    }
})
