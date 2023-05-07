package mjs.data

class Persistence {

    private val addresses = mutableMapOf<Long, Address>()

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