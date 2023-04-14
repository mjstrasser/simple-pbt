package mjs.data

import java.time.LocalDate

val states = listOf("NSW", "VIC", "QLD", "SA", "WA", "TAS", "NT", "ACT")

class Persistence {

    private val people = mutableMapOf<String, Person>()
    private val addresses = mutableMapOf<String, Address>()

    fun savePerson(person: Person) {

        assert(person.name.length < 60)
        assert(person.dateOfBirth < LocalDate.now())

        people[person.id] = person
    }

    fun getPerson(id: String): Person? = people[id]

    fun saveAddress(address: Address) {

        assert(address.line1.length < 100)
        assert(address.line2.length < 100)
        assert(address.line3.length < 100)
        assert((1000..9000).contains(address.postcode))
        assert(states.contains(address.state))
        assert(address.suburb.length < 50)

        addresses[address.id] = address
    }

    fun getAddress(id: String): Address? = addresses[id]

}