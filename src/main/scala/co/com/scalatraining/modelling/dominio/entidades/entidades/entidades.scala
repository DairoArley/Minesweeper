package co.com.scalatraining.modelling.dominio.entidades.entidades


case class Board( matrix : List[Celda], width: Width, height: Height)
case class Minesweeper( board: Board, gameOver: GameOver, mines: Mines)
case class Celda(abscisa: Abscisa, ordenada: Ordenada, valor: Valor, marcada: Marcada, vista: Vista)
sealed trait Dimension
case class Width(n :Int) extends Dimension
case class Height(n : Int) extends Dimension
case class  Abscisa(n :Int)
case class Ordenada(n : Int)
case class Mines(n :Int)
case class Valor(value: String)
case class Vista(view: Boolean)
case class GameOver(boolean: Boolean)
case class Accion(a: String)
case class Marcada(m: Boolean)



