import clojure.lang.PersistentArrayMap
import clojure.lang.PersistentVector
import clojure.`core$assoc_in` as assocIn

fun pArrayMap(vararg ars: Pair<Any, Any>) =
  PersistentArrayMap(ars.flatMap { (k, v) -> listOf(k, v) }.toTypedArray())

fun PersistentArrayMap.pUpdate(path: List<String>, v: Any?) =
  assocIn.invokeStatic(this, path.fold(PersistentVector.EMPTY, { acc, s -> acc.cons(s) }), v)


