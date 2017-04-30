package me.serce.ext1


fun Collection<Int>.toIntArray(): IntArray {
  val result = IntArray(size)
  var index = 0
  for (element in this)
    result[index++] = element
  return result
}


fun main(args: Array<String>) {
}


