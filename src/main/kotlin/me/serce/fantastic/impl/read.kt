package me.serce.fantastic.impl

import clojure.lang.APersistentMap
import clojure.lang.PersistentHashMap


interface Read<out M> {
  val path: Path
  val domain: Domain<M>
}

class Reader<out T>(val p: Path, val dm: Domain<T>) : Focus<Read<T>> {
  override fun narrow(k: String): Focus<Read<T>> = Reader(p.append(k), dm)

  override val op: Read<T> = object : Read<T> {
    override val domain: Domain<T> = dm
    override val path: Path = p
  }
}

inline val <reified V, T> Cursor<Leaf<V>, Read<T>>.value: V
  get() = f.op.path.getIn(f.op.domain.root) as V

class Domain<out T>(val root: APersistentMap = PersistentHashMap.EMPTY) {
  fun update(f: (Mutable) -> Unit): Domain<T> {
    val m = Mutable(root)
    f(m)
    return Domain(m.read(Path.EMPTY) as APersistentMap)
  }
}

val <T> Domain<T>.cursor: Cursor<T, Read<T>>
  get() = Cursor(Reader(Path.EMPTY, this))

fun <T, M> Cursor<M, Read<T>>.update(writer: Cursor<M, Write>.() -> Unit): Domain<T> {
  return f.op.domain.update {
    Cursor<M, Write>(WriterCursor(it, f.op.path)).writer()
  }
}




