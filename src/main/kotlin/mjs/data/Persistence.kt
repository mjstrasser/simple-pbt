package mjs.data

class Persistence {
    // Simulated database.
    private val addresses = mutableMapOf<Long, Address>()

    fun saveAddress(address: Address) {
        // Simulated database constraints.
        assert(address.id in (1_000_000..1_000_000_000_000))
        assert(address.street.length in (2..60))
        assert(address.suburb.length in (2..30))
        assert(address.postcode in (1000..9000))

        addresses[address.id] = address
    }

    // Always return a new object, like from a real database.
    fun getAddress(id: Long): Address? = addresses[id]?.copy()
}
