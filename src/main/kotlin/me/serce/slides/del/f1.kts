import kotlin.reflect.KProperty

class Niffler

class MyWorld {
  val niffler: Niffler by Provider()
}

class Provider {
  operator fun getValue(
    ref: MyWorld,
    prop: KProperty<*>
  ): Niffler {
    return Niffler()
  }
}

println(MyWorld().niffler) // Niffler@7c98a2ab

