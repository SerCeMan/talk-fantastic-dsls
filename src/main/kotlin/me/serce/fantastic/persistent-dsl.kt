package me.serce.fantastic

import me.serce.fantastic.impl.*


interface Guys
val <F> Cursor<Guys, F>.alex by Node<Person>()
val <F> Cursor<Guys, F>.tristan by Node<Person>()

interface Person
val <F> Cursor<Person, F>.age by Leaf<Int>()
val <F> Cursor<Person, F>.name by Leaf<String>()




private fun buildModel() {
  val model = Domain<Guys>()
  val model1 = model.cursor.update {
    alex.age.set(0)
    (alex) {
      age.set(0)
      name.set("rewws")
    }
  }

  assertEquals("rewws", model1.cursor.alex.name.value)

  val model2 = model1.cursor.update {
    alex.name.set("World")
  }

  model2.root

  assertEquals("rewws", model1.cursor.alex.name.value)
  assertEquals("World", model2.cursor.alex.name.value)

  val model3 = model2.cursor.alex.update {
    name.set("No way")
  }
  assertEquals("No way", model3.cursor.alex.name.value)
}


fun main(args: Array<String>) = buildModel()

fun assertEquals(a: Any, b: Any) {
  if (a != b) {
    throw AssertionError()
  }
}
