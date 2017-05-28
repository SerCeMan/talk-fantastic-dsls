package me.serce.fantastic.impl

import kotlin.reflect.KProperty

interface Focus<out Op> {
  fun narrow(k: String): Focus<Op>
  val op: Op
}

class Cursor<out T, out Op>(val f: Focus<Op>)

open class Leaf<out V> {
  open operator fun <Op> getValue(ref: Cursor<*, Op>, property: KProperty<*>): Cursor<Leaf<V>, Op> {
    return Cursor(ref.f.narrow(property.name))
  }
}

open class Node<out T> {
  open operator fun <Op> getValue(ref: Cursor<*, Op>, property: KProperty<*>): Cursor<T, Op> {
    return Cursor(ref.f.narrow(property.name))
  }
}
