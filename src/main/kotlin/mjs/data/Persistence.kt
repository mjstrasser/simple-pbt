package mjs.data

class Persistence {
    private val addresses = mutableMapOf<Long, Address>()

    fun saveAddress(address: Address) {
        assert(address.id in (1_000_000..1_000_000_000_000))
        assert(address.street.length in (2..60))
        assert(address.suburb.length in (2..30))
        assert(address.postcode in (1000..9000))

        addresses[address.id] = address
    }

    fun getAddress(id: Long): Address? = addresses[id]
}
