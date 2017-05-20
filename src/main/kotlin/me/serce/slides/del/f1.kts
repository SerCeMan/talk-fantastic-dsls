import kotlin.reflect.KProperty

interface Creature
class Niffler : Creature


class MyWorld {
  val niffler: Creature? by DI
}

object DI {
  private val map = hashMapOf(
    "niffler" to Niffler()
  )

  operator fun getValue(ref: MyWorld,
    property: KProperty<*>): Creature? {
    return map[property.name]
  }
}

println(MyWorld().niffler) // Niffler@7c98a2ab

