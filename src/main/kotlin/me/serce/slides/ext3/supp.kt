class Gun

val goblin: Goblin = null!!

interface Goblin {
  val peopleAround: Boolean
  val gun: Gun

  fun fire(gun: Gun)
  fun moveLeft()
  fun moveRight()
}
