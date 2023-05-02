package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.localDate
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.orNull
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.enum
import mjs.kotest.description
import java.time.LocalDate

class PersistencePropertyTests : DescribeSpec({
    description("Property-based tests of persisting values to the simple database.")

    val ids = Arb.long(1_000_000, 1_000_000_000_000)
    val names = Arb.string(1, 60)
    val dobs = Arb.localDate(LocalDate.of(1870, 1, 1), LocalDate.now())
    val addressLines = Arb.string(2, 100)
    val suburbs = Arb.string(2, 50)
    val states = Exhaustive.enum<State>()
    val postcodes = Arb.int(1000, 9000)

    describe("persisting people") {
        val persistence = Persistence()
        it("gets a person who was saved, identified by their ID") {
            checkAll(ids, names, dobs) { id, name, dob ->
                val person = Person(id, name, dob)
                persistence.savePerson(person)

                persistence.getPerson(id) shouldBe person
            }
        }
        it("updates a person, identified by their ID") {
            checkAll(ids, names, names, dobs) { id, oldName, newName, dob ->
                val person = Person(id, oldName, dob)
                persistence.savePerson(person)
                persistence.savePerson(person.copy(name = newName))

                persistence.getPerson(id)?.name shouldBe newName
            }
        }
    }

    describe("persisting addresses") {
        val persistence = Persistence()
        it("gets an address that was saved, identified by ID") {
            checkAll(
                ids,
                addressLines,
                addressLines.orNull(),
                suburbs,
                states,
                postcodes
            ) { id, line1, line2, suburb, state, postcode ->
                val address = Address(id, line1, line2, suburb, state, postcode)
                persistence.saveAddress(address)

                persistence.getAddress(id) shouldBe address
            }
        }
        it("updates an address, identified by ID") {
            checkAll(
                ids,
                addressLines,
                addressLines,
                addressLines.orNull(),
                suburbs,
                states,
                postcodes
            ) { id, oldLine1, newLine1, line2, suburb, state, postcode ->
                val address = Address(id, oldLine1, line2, suburb, state, postcode)
                persistence.saveAddress(address)
                persistence.saveAddress(address.copy(line1 = newLine1))

                persistence.getAddress(id)?.line1 shouldBe newLine1
            }
        }
    }
})