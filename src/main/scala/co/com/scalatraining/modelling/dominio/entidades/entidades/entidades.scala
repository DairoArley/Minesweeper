package co.com.scalatraining.modelling.dominio.entidades.entidades


case class Board( matrix : List[List[Celda]])
case class Celda(abscisa: Abscisa, ordenada: Ordenada, valor: Valor, vista: Vista)
sealed trait Dimension
case class Width(n :Int) extends Dimension
case class Height(n : Int) extends Dimension
case class  Abscisa(n :Int)
case class Ordenada(n : Int)
case class Mines(n :Int)
case class Valor(value: Char)
case class Vista(view: Boolean)


