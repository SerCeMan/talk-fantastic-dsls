data class Transaction(val payment: Payment,
                       val parts: Parts)
data class Payment(val currency: String,
                   val amount: Int)
data class Parts(val from: Person, val to: Person)
data class Person(val id: Int, val name: String)
