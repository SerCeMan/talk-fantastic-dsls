package me.serce.fantastic.impl

import clojure.lang.APersistentMap as PMap
import clojure.lang.PersistentArrayMap as PArrayMap
import kotlin.reflect.KClass

interface Write {
  fun read(): Any?
  fun write(a: Any?)
}

class Mutable(var m: Any?) {
  fun write(p: Path, a: Any?) {
    m = p.assocIn(m, a)
  }

  fun read(p: Path) = p.getIn(m)
}

class WriterCursor(val m: Mutable, val path: Path) : Focus<Write> {
  override fun narrow(k: String): Focus<Write> = WriterCursor(m, path.append(k))

  override val op: Write = object : Write {
    override fun write(a: Any?) = m.write(path, a)
    override fun read(): Any? = m.read(path)
  }
}

fun <T, M> Cursor<M, Read<T>>.update(update: Cursor<M, Write>.() -> Unit): Domain<T> {
  val m = Mutable(f.op.domain.root)
  Cursor<M, Write>(WriterCursor(m, f.op.path)).update()
  return Domain(m.read(Path.EMPTY) as PMap)
}

fun <M> domain(f: Cursor<M, Write>.() -> Unit) = Domain<M>().cursor.update(f)

fun Write.init(k: KClass<*>) {
  if (read() == null) {
    write(when (k) {
      Leaf::class -> null
      else -> PArrayMap.EMPTY
    })
  }
}

operator inline fun <reified T> Cursor<T, Write>.invoke(init: Cursor<T, Write>.() -> Unit): Unit {
  f.op.init(T::class)
  init()
}

fun <T> Cursor<Leaf<T>, Write>.set(t: T): Unit {
  f.op.write(t)
}
