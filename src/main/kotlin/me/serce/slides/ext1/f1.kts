fun String.removeSpaces(): String {
  return this.filter { c -> c != ' ' }
}

print("Hi ! , ext".removeSpaces()) // "Hi!,ext"
