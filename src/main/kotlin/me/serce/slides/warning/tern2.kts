class Ternary<T>(val expr: Boolean, val then: T) {
  operator fun div(elsе: T): T = if (expr) then else elsе
}

operator fun <T> Boolean.rem(a: T): Ternary<T> = Ternary(this, a)

val result = (5 == 2 + 3) % 1 / 2 // 1
