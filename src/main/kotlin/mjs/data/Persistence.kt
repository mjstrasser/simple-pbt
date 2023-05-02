package mjs.data

import java.time.LocalDate

class Persistence {

    private val people = mutableMapOf<Long, Person>()
    private val addresses = mutableMapOf<Long, Address>()

    fun savePerson(person: Person) {

        assert(person.id in (1_000_000..1_000_000_000_000))
        assert(person.name.length in (1..60))
        assert(person.dateOfBirth <= LocalDate.now())

        people[person.id] = person
    }

    fun getPerson(id: Long): Person? = people[id]

    fun saveAddress(address: Address) {

        assert(address.id in (1_000_000..1_000_000_000_000))
        assert(address.line1.length in (2..100))
        if (address.line2 != null) assert(address.line2.length in (2..100))
        assert(address.suburb.length in (2..50))
        assert(address.postcode in (1000..9000))

        addresses[address.id] = address
    }

    fun getAddress(id: Long): Address? = addresses[id]

}