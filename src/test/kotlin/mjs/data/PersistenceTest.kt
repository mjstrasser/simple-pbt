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
        it("updates a person with the same ID as before") {
            val almaMahler = Person("123457", "Alma Mahler", LocalDate.of(1879, 8, 31))
            persistence.savePerson(almaMahler)

            val almaGropius = Person("123457", "Alma Gropius", LocalDate.of(1879, 8, 31))
            persistence.savePerson(almaGropius)

            persistence.getPerson("123457")?.name shouldBe "Alma Gropius"
        }
    }
})