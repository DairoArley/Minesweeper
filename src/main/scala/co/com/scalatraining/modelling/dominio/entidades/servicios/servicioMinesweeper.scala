package co.com.scalatraining.modelling.dominio.entidades.servicios

import co.com.scalatraining.modelling.dominio.entidades.entidades._

sealed trait AlgebraServicioMinesweeper {
  def imprimirBoard()
  def construirBoard(width: Width, height: Height, mines: Mines):Board
  def paresAleatorios(width: Width, height: Height, mines: Mines):List[Tuple2[Abscisa, Ordenada]]
  def insertarValor(list: List[List[Celda]], abscisa: Abscisa, ordenada: Ordenada, valor: Valor):List[List[Celda]]
}

sealed trait InterpretacionServicioMinesweeper extends AlgebraServicioMinesweeper{
  override def imprimirBoard(): Unit = ???

  override def construirBoard(width: Width, height: Height, mines: Mines): Board = {
    val random = scala.util.Random
    val list1 = List.range(0,width.n)
    val list2 = list1.map( x => List( Celda(Abscisa(x), Ordenada(0), Valor('.'), Vista(false) ) ) )
    val list3 = list2.map( x => List.range(0,height.n).map( y => List( Celda(x.head.abscisa , Ordenada(y), Valor('.'), Vista(false) ) ) ) )
    val list4 = list3.flatten(x => x)
    val coordenadas = paresAleatorios(width, height, mines)
    //val dos = List.range(1,9)
    //val list5 = list4.map(x => x.map(y => {coordenadas.map(z => {
      //if (z._1 == y.abscisa && z._2 == y.ordenada){
      //Celda(y.abscisa, y.ordenada, Valor(random.nextInt(9).toChar), Vista(false))
      //}else
        //Celda(Abscisa(y.abscisa.n), Ordenada(y.ordenada.n), Valor(y.valor.value), Vista(y.vista.view) )
    //} ) }))
    //val list6 = list5.flatten(x => x)

    //val list7 = match {

    //}
    //val otoro = coordenadas.foldLeft()
    val recorrido = List(list4)
    val trazo = coordenadas.foldLeft(recorrido){(resultado, item) => resultado :+ insertarValor(resultado.last, item._1,item._2, Valor(random.nextInt(9).toChar))
    val list8 = trazo
    Board(list8)
  }

  override def insertarValor(list: List[List[Celda]], abscisa: Abscisa, ordenada: Ordenada, valor: Valor): List[List[Celda]] = {
    val r = scala.util.Random
    val list1 = list.map( x=> x.map( y => {
      if (y.abscisa.n == abscisa.n && y.ordenada.n == ordenada.n) {
        Celda(abscisa, ordenada, Valor(r.nextInt(9).toChar), Vista(false))
      } else
        y
    }
    ))
    list1
  }

  override def paresAleatorios(width: Width, height: Height, mines: Mines): List[(Abscisa, Ordenada)] = {
    val r = scala.util.Random
    val list = List.range(1,mines.n).map( x => Tuple2( Abscisa( r.nextInt(width.n+1) ), Ordenada( r.nextInt(height.n+1)) ) )
    list
  }


}

object InterpretacionServicioMinesweeper extends InterpretacionServicioMinesweeper