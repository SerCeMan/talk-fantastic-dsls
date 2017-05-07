fun String.removeSpaces(): String {
  return this.filter({ c -> c != ' ' })
}
