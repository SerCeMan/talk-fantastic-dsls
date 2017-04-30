package me.serce.fantastic.impl

import clojure.`core$assoc_in` as assocIn
import clojure.`core$get_in` as getIn
import clojure.lang.*

data class Path(private val v: APersistentVector) {
  companion object {
    val EMPTY = Path(PersistentVector.EMPTY)
  }

  fun append(a: String): Path = Path(v.cons(a) as APersistentVector)

  fun getIn(model: Any?): Any? = when {
    v.isEmpty() -> model
    else -> getIn.invokeStatic(model, v)
  }

  fun assocIn(m: Any?, a: Any?): Any? = when {
    v.isEmpty() -> a
    else -> assocIn.invokeStatic(m, v, a)
  }
}
