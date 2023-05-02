package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.arbitrary.orNull
import io.kotest.property.checkAll
import mjs.helpers.genAddressLine
import mjs.helpers.genDob
import mjs.helpers.genId
import mjs.helpers.genName
import mjs.helpers.genPostcode
import mjs.helpers.genState
import mjs.helpers.genSuburb
import mjs.kotest.description

class PersistencePropertyTests : DescribeSpec({
    description("Property-based tests of persisting values to the simple database.")

    describe("persisting people") {
        val persistence = Persistence()
        it("gets a person who was saved, identified by their ID") {
            checkAll(genId, genName, genDob) { id, name, dob ->
                val person = Person(id, name, dob)
                persistence.savePerson(person)

                persistence.getPerson(id) shouldBe person
            }
        }
        it("changes the name of a person, identified by their ID") {
            checkAll(genId, genName, genName, genDob) { id, oldName, newName, dob ->
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
                genId,
                genAddressLine,
                genAddressLine.orNull(),
                genSuburb,
                genState,
                genPostcode
            ) { id, line1, line2, suburb, state, postcode ->
                val address = Address(id, line1, line2, suburb, state, postcode)
                persistence.saveAddress(address)

                persistence.getAddress(id) shouldBe address
            }
        }
        it("updates an address, identified by ID") {
            checkAll(
                genId,
                genAddressLine,
                genAddressLine,
                genAddressLine.orNull(),
                genSuburb,
                genState,
                genPostcode
            ) { id, oldLine1, newLine1, line2, suburb, state, postcode ->
                val address = Address(id, oldLine1, line2, suburb, state, postcode)
                persistence.saveAddress(address)
                persistence.saveAddress(address.copy(line1 = newLine1))

                persistence.getAddress(id)?.line1 shouldBe newLine1
            }
        }
    }
})