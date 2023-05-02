package mjs.data

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mjs.helpers.randomAddressLine
import mjs.helpers.randomId
import mjs.helpers.randomName
import mjs.helpers.randomPastDate
import mjs.helpers.randomPostcode
import mjs.helpers.randomState
import mjs.helpers.randomSuburb

class PersistenceRandomTests : DescribeSpec({
    describe("persisting people") {
        val persistence = Persistence()
        it("gets a person who was saved") {
            val id = randomId()
            val person = Person(id, randomName(), randomPastDate(150))
            persistence.savePerson(person)

            persistence.getPerson(id) shouldBe person
        }
        it("changes the name of a person identified by their ID") {
            val id = randomId()
            val person = Person(id, randomName(), randomPastDate(150))
            persistence.savePerson(person)

            val newName = randomName()
            val personWithNewName = person.copy(name = newName)
            persistence.savePerson(personWithNewName)

            persistence.getPerson(id)?.name shouldBe newName
        }
    }
    describe("persisting addresses") {
        val persistence = Persistence()
        it("gets an address that was saved") {
            val id = randomId()
            val address = Address(
                id = id,
                line1 = randomAddressLine(),
                suburb = randomSuburb(),
                state = randomState(),
                postcode = randomPostcode(),
            )
            persistence.saveAddress(address)

            persistence.getAddress(id) shouldBe address
        }
        it("updates an address identified by ID") {
            val id = randomId()
            val suburb = randomSuburb()
            val state = randomState()
            val postcode = randomPostcode()
            val address = Address(
                id = id,
                line1 = randomAddressLine(),
                suburb = suburb,
                state = state,
                postcode = postcode,
            )
            persistence.saveAddress(address)

            val newLine1 = randomAddressLine()
            persistence.saveAddress(address.copy(line1 = newLine1))

            persistence.getAddress(id)?.line1 shouldBe newLine1
        }
    }
})
