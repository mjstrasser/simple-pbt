package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mjs.kotest.description

class PersistenceExampleTests : DescribeSpec({
    description("Example tests of persisting values to the simple database.")
    describe("persisting addresses") {
        val persistence = Persistence()
        it("gets an address that was saved, identified by ID") {
            val twBneId = 7654321L
            val address = Address(
                id = twBneId,
                line1 = "129 Creek Street",
                suburb = "Brisbane",
                state = State.QLD,
                postcode = 4000,
            )
            persistence.saveAddress(address)

            persistence.getAddress(twBneId) shouldBe address
        }
        it("updates an address, identified by ID") {
            val twSydId = 7654322L
            val wrongStreetNumber = Address(
                id = twSydId,
                line1 = "52 Carrington Street",
                suburb = "Sydney",
                state = State.NSW,
                postcode = 2000,
            )
            persistence.saveAddress(wrongStreetNumber)

            val rightStreetNumber = wrongStreetNumber.copy(line1 = "50 Carrington Street")
            persistence.saveAddress(rightStreetNumber)

            persistence.getAddress(twSydId)?.line1 shouldBe "50 Carrington Street"
        }
    }
})
