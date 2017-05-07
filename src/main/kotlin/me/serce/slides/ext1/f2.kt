// from stdlib
public inline fun String.filter(
  predicate: (Char) -> Boolean): String {
  val destination = StringBuilder()
  for (index in 0..length - 1) {
    val element = get(index)
    if (predicate(element))
      destination.append(element)
  }
  return destination.toString()
}
