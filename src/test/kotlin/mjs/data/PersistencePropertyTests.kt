package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import mjs.helpers.genId
import mjs.helpers.genPostcode
import mjs.helpers.genState
import mjs.helpers.genStreet
import mjs.helpers.genSuburb
import mjs.kotest.description

class PersistencePropertyTests : DescribeSpec({
    description("Property-based tests of persisting values to the simple database.")
    describe("persisting addresses") {
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
        it("updates an address, identified by ID") {
            val persistence = Persistence()
            checkAll(
                genId,
                genStreet,
                genStreet,
                genSuburb,
                genState,
                genPostcode
            ) { id, street1, street2, suburb, state, postcode ->

                val address1 = Address(id, street1, suburb, state, postcode)
                persistence.saveAddress(address1)

                val address2 = address1.copy(street = street2)
                persistence.saveAddress(address2)

                persistence.getAddress(id) shouldBe address2
            }
        }
    }
})