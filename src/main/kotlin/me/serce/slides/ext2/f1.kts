fun String.map(f: (Char) -> Char): String {
  return toCharArray().map(f).joinToString("")
}

val cypher = "Hello, DSL".map { it + 1 }
println(cypher) // Ifmmp-!ETM
