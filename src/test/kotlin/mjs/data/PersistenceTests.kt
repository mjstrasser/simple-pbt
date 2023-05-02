package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class PersistenceTests : DescribeSpec({
    describe("persisting people") {
        val persistence = Persistence()
        it("gets a person who was saved, identified by their ID") {
            val walterId = 1234567L
            val walter = Person(walterId, "Walter Gropius", LocalDate.of(1883, 5, 18))
            persistence.savePerson(walter)

            persistence.getPerson(walterId) shouldBe walter
        }
        it("updates a person, identified by ID") {
            val almaId = 1234568L
            val almaMahler = Person(almaId, "Alma Mahler", LocalDate.of(1879, 8, 31))
            persistence.savePerson(almaMahler)

            val almaGropius = almaMahler.copy(name = "Alma Gropius")
            persistence.savePerson(almaGropius)

            persistence.getPerson(almaId)?.name shouldBe "Alma Gropius"
        }
    }
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