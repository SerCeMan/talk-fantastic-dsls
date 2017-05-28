class Gun

val droid: Droid = null!!

interface Droid {
  val peopleAround: Boolean
  val gun: Gun

  fun fire(gun: Gun)
  fun moveLeft()
  fun moveRight()
}
