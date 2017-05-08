package me.serce.fantastic

import me.serce.fantastic.impl.*

interface Transaction
val <F> Cursor<Transaction, F>.person by Node<Person>()
val <F> Cursor<Transaction, F>.payment by Node<Payment>()

interface Person
val <F> Cursor<Person, F>.id by Leaf<Int>()
val <F> Cursor<Person, F>.name by Leaf<String>()

interface Payment
val <F> Cursor<Payment, F>.currency by Leaf<String>()
val <F> Cursor<Payment, F>.amount by Leaf<Int>()



private fun buildModel() {
  val model = Domain<Transaction>()
  val model1 = model.cursor.update {
    (person) {
      id.set(0)
      name.set("alex")
    }
    (payment) {
      currency.set("AUD")
      amount.set("15")
    }
  }

  model.cursor.person.id.value

  val payment: Cursor<Payment, Read<Transaction>> = model1.cursor.payment
  payment.amount.value

  assertEquals("alex", model1.cursor.person.name.value)

  val model2 = model1.cursor.update {
    person.name.set("World")
  }

  model2.root

  assertEquals("alex", model1.cursor.person.name.value)
  assertEquals("World", model2.cursor.person.name.value)

  val model3 = model2.cursor.person.update {
    name.set("No way")
  }
  assertEquals("No way", model3.cursor.person.name.value)
}


fun main(args: Array<String>) = buildModel()

fun assertEquals(a: Any, b: Any) {
  if (a != b) {
    throw AssertionError()
  }
}
