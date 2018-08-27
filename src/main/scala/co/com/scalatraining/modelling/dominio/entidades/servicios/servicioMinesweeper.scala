package co.com.scalatraining.modelling.dominio.entidades.servicios

import co.com.scalatraining.modelling.dominio.entidades.entidades._

sealed trait AlgebraServicioMinesweeper {
  def imprimirBoard(board: Board)
  def construirBoard(width: Width, height: Height, mines: Mines):Board
  def paresAleatorios(width: Width, height: Height, mines: Mines):List[Tuple2[Abscisa, Ordenada]]
  def insertarValores(list1: List[Celda], list2: List[Tuple2[Abscisa, Ordenada]]):List[Celda]
  def insertarCelda(celda: Celda, list: List[(Abscisa, Ordenada)]): Celda
}

sealed trait InterpretacionServicioMinesweeper extends AlgebraServicioMinesweeper{
  override def imprimirBoard(board: Board): Unit = {
    board.matrix.foreach(x => {
      if(x.vista.view){
        if (x.ordenada.n == board.width.n-1){
          println(x.valor.value)
        }else{
          print(s"${x.valor.value}${" "}")
        }
      }else{
        if (x.ordenada.n == board.width.n-1){
          println(".")
        }else{
          println(". ")
        }
      }
    } )
  }

  override def construirBoard(width: Width, height: Height, mines: Mines): Board = {
    val list1 = List.range(0, width.n)
    val list2 = list1.map(x => List(Celda(Abscisa(x), Ordenada(0), Valor('.'), Vista(false))))
    val list3 = list2.map(x => List.range(0, height.n).map(y => List(Celda(x.head.abscisa, Ordenada(y), Valor('.'), Vista(false)))))
    val list4 = list3.flatten(x => x).flatten(y => y)
    val coordenadas = paresAleatorios(width, height, mines)
    val list5 = insertarValores(list4, coordenadas)
    Board(list5, width, height)
  }
  override def insertarValores(list1: List[Celda], list2: List[Tuple2[Abscisa, Ordenada]]):List[Celda] = {
    val list = list1.map(x =>  insertarCelda(x, list2) )
    list
  }

  override def insertarCelda(celda: Celda, list1: List[(Abscisa, Ordenada)]): Celda = {
    val r = new scala.util.Random
    val list = list1.map(x => if (x._1.n == celda.abscisa.n && x._2.n == celda.ordenada.n){
      Celda(celda.abscisa, celda.ordenada, Valor(r.nextInt(9).toChar), Vista(false) )
    }else{ celda} )
    list.head
  }

  override def paresAleatorios(width: Width, height: Height, mines: Mines): List[(Abscisa, Ordenada)] = {
    val r = new scala.util.Random
    val list = List.range(1,mines.n+1).map( x => Tuple2( Abscisa( r.nextInt(width.n+1) ), Ordenada( r.nextInt(height.n+1)) ) )
    list
  }


}

object InterpretacionServicioMinesweeper extends InterpretacionServicioMinesweeper