package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mjs.kotest.description

class PersistenceExampleTests : DescribeSpec({
    description("Example-based tests of persisting values to the simple database.")
    describe("persisting addresses") {
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
        it("updates an address, identified by ID") {
            val persistence = Persistence()
            val twSydId = 7654322L
            val wrongStreetAddress = Address(
                id = twSydId,
                street = "Level 20, 52 Carrington Street",
                suburb = "Sydney",
                state = State.NSW,
                postcode = 2000,
            )
            persistence.saveAddress(wrongStreetAddress)

            val rightStreetAddress = wrongStreetAddress
                .copy(street = "Level 20, 50 Carrington Street")
            persistence.saveAddress(rightStreetAddress)

            persistence.getAddress(twSydId) shouldBe rightStreetAddress
        }
    }
})
