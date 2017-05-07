// update 1
val stansTrs = Transaction(trs.payment, Parts(
  Person(trs.parts.from.id, "stan"),
  trs.parts.to)
)
