package me.serce.fantastic.impl

import clojure.lang.PersistentArrayMap
import kotlin.reflect.KClass


interface Write {
  fun read(): Any?
  fun write(a: Any?)
}

class WriterCursor(val m: Mutable, val path: Path) : Focus<Write> {
  override fun narrow(k: String): Focus<Write> = WriterCursor(m, path.append(k))

  override val op: Write = object : Write {
    override fun write(a: Any?) = m.write(path, a)
    override fun read(): Any? = m.read(path)
  }
}

fun Write.init(k: KClass<*>) {
  if (read() == null) {
    write(when (k) {
      Leaf::class -> null
      else -> PersistentArrayMap.EMPTY
    })
  }
}

operator inline fun <reified T> Cursor<T, Write>.invoke(builder: Cursor<T, Write>.() -> Unit): Unit {
  f.op.init(T::class)
  builder()
}

fun <T> Cursor<Leaf<T>, Write>.set(t: T): Unit {
  f.op.write(t)
}

class Mutable(var m: Any?) {
  fun write(p: Path, a: Any?) {
    m = p.assocIn(m, a)
  }

  fun read(p: Path) = p.getIn(m)
}
