package me.serce.dsl1


fun main(args: Array<String>) {
  val list = listOf("Kotlin", "is", "awesome")
  val result: HTML =
    html {
      head {
        title { +"XML encoding with Kotlin" }
      }
      body {
        p {
          +"markup to XML"
          +"another"
        }
        a(href = "jetbrains.com/kotlin") {
          +"Kotlin"
        }
        p {
          +"Command line arguments were:"
          ul {
            for (arg in list)
              li { +arg }
          }
        }
      }
    }
  println(result)
}

interface Element {
  fun render(builder: StringBuilder, indent: String)
}

abstract class Tag(val name: String) : Element {
  val children = arrayListOf<Element>()
  val attributes = hashMapOf<String, String>()

  override fun render(builder: StringBuilder, indent: String) {
    builder.append("$indent<$name${renderAttributes()}>\n")
    for (c in children) {
      c.render(builder, indent + "  ")
    }
    builder.append("$indent</$name>\n")
  }

  private fun renderAttributes(): String {
    return attributes.map { (k, v) -> " $k=\"$v\"" }.joinToString("")
  }

  protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): Unit {
    tag.init()
    children.add(tag)
  }

  operator fun String.unaryPlus() {
    children.add(TextElement(this))
  }

  override fun toString(): String {
    val builder = StringBuilder()
    render(builder, "")
    return builder.toString()
  }
}

class TextElement(val text: String) : Element {
  override fun render(builder: StringBuilder, indent: String) {
    builder.append("$indent$text\n")
  }
}

// impl

fun html(init: HTML.() -> Unit): HTML {
  val html = HTML()
  html.init()
  return html
}

class HTML : Tag("html") {
  fun head(init: Head.() -> Unit) = initTag(Head(), init)

  fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head : Tag("head") {
  @Deprecated(message = "wrong scope", level = DeprecationLevel.ERROR)
  fun head(init: Head.() -> Unit) = initTag(Head(), init)

  fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title : Tag("title")

abstract class BodyTag(name: String) : Tag(name) {
  fun p(init: P.() -> Unit) = initTag(P(), init)
  fun ul(init: UL.() -> Unit) = initTag(UL(), init)
  fun a(href: String, init: A.() -> Unit) {
    val a = A()
    initTag(a, init)
    a.href = href
  }
}

class Body : BodyTag("body")
class UL : BodyTag("ul") {
  fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

class LI : BodyTag("li")
class P : BodyTag("p")

class A : BodyTag("a") {
  var href: String
    get() = attributes["href"] ?: ""
    set(value) {
      attributes["href"] = value
    }
}

