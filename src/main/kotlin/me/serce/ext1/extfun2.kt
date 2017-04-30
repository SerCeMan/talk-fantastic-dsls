package me.serce.ext1


fun String.removeSpaces(): String {
  return this.filter { it != ' ' }
}

fun String.map(f: (Char) -> Char): String {
  return this.toCharArray().map { f(it) }.joinToString("")
}


class Gun

class Droid(val peopleAround: Boolean,
            val gun: Gun) {
  fun fire(gun: Gun) {}
  fun moveLeft() {}
  fun moveRight() {}
}

val droid = Droid(true, gun = Gun())
val currentCmd = null

fun on(cmd: String, f: Droid.() -> Unit) {
  if (cmd == currentCmd) {
    droid.f()
  }
}

fun String.mapReciever(f: String.(Char) -> String): String {
  return this.toCharArray().map { f(it) }.joinToString("")
}


fun main(args: Array<String>) {
  val nospaces = "Hello, DSL".removeSpaces()
  println(nospaces)

  val cypher = "Hello, DSL".map { it + 1 }
  println(cypher)

  val cypher2 = "Hello, DSL".mapReciever { this + it }
  println(cypher2)


  on("back") {
    moveLeft()
    if (peopleAround) {
      fire(gun)
    }
  }
}


