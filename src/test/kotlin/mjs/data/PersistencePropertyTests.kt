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
            checkAll(genId, genStreet, genSuburb, genState, genPostcode) { id, street, suburb, state, postcode ->
                val address = Address(id, street, suburb, state, postcode)
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
            ) { id, oldStreet, newStreet, suburb, state, postcode ->
                val address = Address(id, oldStreet, suburb, state, postcode)
                persistence.saveAddress(address)
                persistence.saveAddress(address.copy(street = newStreet))

                persistence.getAddress(id)?.street shouldBe newStreet
            }
        }
    }
})