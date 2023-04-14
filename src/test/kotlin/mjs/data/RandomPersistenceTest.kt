package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mjs.helpers.randomId
import mjs.helpers.randomName
import java.time.LocalDate

class RandomPersistenceTest : DescribeSpec({
    describe("persisting people") {
        val persistence = Persistence()
        it("gets a person who was saved") {
            val id = randomId()
            val walter = Person(id, randomName(), LocalDate.of(1883, 5, 18))
            persistence.savePerson(walter)

            persistence.getPerson(id) shouldBe walter
        }
        it("updates a person identified by ID") {
            val id = randomId()
            val almaMahler = Person(id, randomName(), LocalDate.of(1879, 8, 31))
            persistence.savePerson(almaMahler)

            val newName = randomName()
            val almaGropius = Person(id, newName, LocalDate.of(1879, 8, 31))
            persistence.savePerson(almaGropius)

            persistence.getPerson(id)?.name shouldBe newName
        }
    }
    describe("persisting addresses") {
        val persistence = Persistence()
        it("gets an address that was saved") {
            val address = Address(
                id = "654321",
                line1 = "129 Creek Street",
                suburb = "Brisbane",
                state = "QLD",
                postcode = 4000,
            )
            persistence.saveAddress(address)

            persistence.getAddress("654321") shouldBe address
        }
        it("updates an address identified by ID") {
            persistence.saveAddress(
                Address(
                    id = "654322",
                    line1 = "52 Carrington Street",
                    suburb = "Sydney",
                    state = "NSW",
                    postcode = 2000,
                )
            )
            persistence.saveAddress(
                Address(
                    id = "654322",
                    line1 = "50 Carrington Street",
                    suburb = "Sydney",
                    state = "NSW",
                    postcode = 2000,
                )
            )

            persistence.getAddress("654322")?.line1 shouldBe "50 Carrington Street"
        }
    }
})