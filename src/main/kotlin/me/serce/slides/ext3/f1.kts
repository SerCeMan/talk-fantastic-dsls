interface Drоid {
  val peopleAround: Boolean
  val gun: Gun

  fun fire(gun: Gun)
  fun moveLeft()
  fun moveRight()
}

fun on(cmd: String, f: Droid.() -> Unit) {
// ...
  droid.f()
// ...
}

//

on("back") {
  moveLeft()
  if (peopleAround) {
    fire(gun)
  }
}
