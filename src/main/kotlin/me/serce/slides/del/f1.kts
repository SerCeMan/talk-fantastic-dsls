import kotlin.reflect.KProperty

class Niffler

class MyWorld {
  val niffler: Niffler? by Provider()
  val creature: Niffler? by Provider()
}

class Provider {
  operator fun getValue(ref: MyWorld,
                        prop: KProperty<*>
  ): Niffler? {
    return when (prop.name) {
      "niffler" -> Niffler()
      else -> null
    }
  }
}

println(MyWorld().niffler) // Niffler@7c98a2ab
println(MyWorld().creature) // null

