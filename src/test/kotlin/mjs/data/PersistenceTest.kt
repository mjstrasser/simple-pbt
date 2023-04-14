package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class PersistenceTest : DescribeSpec({
    describe("persisting people") {
        val persistence = Persistence()
        it("gets a person who was saved") {
            val walter = Person("123456", "Walter Gropius", LocalDate.of(1883, 5, 18))
            persistence.savePerson(walter)

            persistence.getPerson("123456") shouldBe walter
        }
        it("updates a person identified by ID") {
            val almaMahler = Person("123457", "Alma Mahler", LocalDate.of(1879, 8, 31))
            persistence.savePerson(almaMahler)

            val almaGropius = Person("123457", "Alma Gropius", LocalDate.of(1879, 8, 31))
            persistence.savePerson(almaGropius)

            persistence.getPerson("123457")?.name shouldBe "Alma Gropius"
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