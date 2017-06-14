val list = listOf("Kotlin", "is", "awesome")
val result: HTML =
  html {
    head {
      title { +"HTML DSL in Kotlin" }
    }
    body {
      p {
        +"a line about Kotlin"
      }
      a(href = "http://jetbrains.com/kotlin") {
        +"Kotlin"
      }
      p {
        +"Kotlin is:"
        ul {
          for (arg in list)
            li { +arg }
        }
      }
    }
  }
println(result)
