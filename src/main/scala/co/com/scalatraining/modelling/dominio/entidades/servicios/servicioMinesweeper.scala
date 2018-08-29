package co.com.scalatraining.modelling.dominio.entidades.servicios

import java.security.KeyStore.TrustedCertificateEntry

import co.com.scalatraining.modelling.dominio.entidades.entidades
import co.com.scalatraining.modelling.dominio.entidades.entidades._

sealed trait AlgebraServicioMinesweeper {
  def imprimirMinesweeper(minesweeper: Minesweeper)
  def construirMinesweeper(width: Width, height: Height, mines: Mines):Minesweeper
  def paresAleatorios(width: Width, height: Height, mines: Mines):List[Tuple2[Abscisa, Ordenada]]
  def insertarValores(list1: List[Celda], list2: List[Tuple2[Abscisa, Ordenada]]):List[Celda]
  def insertarCelda(celda: Celda, list: List[(Abscisa, Ordenada)]): Celda
  def realizarAccion(board: Board, ordenada: Ordenada, abscisa: Abscisa, accion: Accion, mines: Mines):Minesweeper
  def mostrarCeldasAdyacentes(board: Board, celda: Celda):List[Celda]
  def isDentroRango(celda: Celda, width: Width, height: Height):Boolean
  def insertarValor(listAdyacentes: List[Celda], list: List[Celda], celda: Celda ): List[Celda]
  def juegoPerdido(minesweeper: Minesweeper):Boolean
  def juegoTerminado(minesweeper: Minesweeper):Boolean
}

sealed trait InterpretacionServicioMinesweeper extends AlgebraServicioMinesweeper{
  override def imprimirMinesweeper(minesweeper: Minesweeper): Unit = {
    minesweeper.board.matrix.foreach(x => {
      if(x.marcada.m){
        print("P ")
      }else{
          if(x.vista.view){
            print(s"${x.vista.view}${" "}")
          }else{
            print(". ")
          }
      }
      if(x.ordenada.n == minesweeper.board.width.n){ println(" ") }
    } )
  }

  override def construirMinesweeper(width: Width, height: Height, mines: Mines): Minesweeper = {
    val list1 = List.range(1, width.n+1)
    val list2 = list1.map(x => List(Celda(Abscisa(x), Ordenada(1), Valor("."), Marcada(false), Vista(false))))
    val list3 = list2.map(x => List.range(1, height.n+1).map(y => List(Celda(x.head.abscisa, Ordenada(y), Valor("."), Marcada(false), Vista(false)))))
    val list4 = list3.flatten(x => x).flatten(y => y)
    val coordenadas = paresAleatorios(width, height, mines)
    val list5 = insertarValores(list4, coordenadas)
    Minesweeper(Board(list5, width, height), GameOver(false), mines )
  }
  override def insertarValores(list1: List[Celda], list2: List[Tuple2[Abscisa, Ordenada]]):List[Celda] = {
    val list = list1.map(x =>  insertarCelda(x, list2) )
    list
  }

  override def insertarCelda(celda: Celda, list1: List[(Abscisa, Ordenada)]): Celda = {
    val list = list1.map(x => if (x._1.n == celda.abscisa.n && x._2.n == celda.ordenada.n){
      Celda(celda.abscisa, celda.ordenada, Valor("*"), Marcada(false), Vista(false) )
    }else{ celda } )
    list.head
  }

  override def realizarAccion(board: Board, ordenada: Ordenada, abscisa: Abscisa, accion: Accion, mines: Mines): Minesweeper = {
    var what = false
    var celda =Celda(Abscisa(0), Ordenada(0), Valor("0"), Marcada(false), Vista(true) )
    val list = board.matrix.map(x => {
      if (x.abscisa == abscisa && x.ordenada == ordenada){
            if (accion.a == "U"){
                  if(x.valor.value == "."){
                    println("Great"); what = true; celda = x
                    Celda(x.abscisa, x.ordenada, Valor("-"), x.marcada, Vista(true) )
                  }else if(x.valor.value == "-"){
                    println("It was dismark")
                    Celda(x.abscisa, x.ordenada, x.valor, x.marcada, x.vista )
                  }else if(x.marcada.m){
                    println("Should dismark in order to uncover")
                    Celda(x.abscisa, x.ordenada, x.valor, x.marcada, Vista(true) )
                  }else {
                    println("Failed")
                    Celda(x.abscisa, x.ordenada, Valor("*"), x.marcada, Vista(true) )
                  }
            }else if( accion.a == "M"){
                  if(x.valor.value == "."){
                    println("Marked")
                    Celda(x.abscisa, x.ordenada, Valor("P"), Marcada(true), x.vista )
                  }else if(x.valor.value == "-"){
                    println("It's not neccesary to mark")
                    Celda(x.abscisa, x.ordenada, x.valor, x.marcada, x.vista )
                  }else if(x.marcada.m){
                    println("Dismark")
                    Celda(x.abscisa, x.ordenada, x.valor, Marcada(false), x.vista )
                  }else{
                    println("Mark")
                    Celda(x.abscisa, x.ordenada, x.valor, Marcada(true), x.vista )
                  }
            }else
              println("No se puede hacer la acción")
              x
      }else x
    })

    if(what){
      val list2 = mostrarCeldasAdyacentes(Board(list, board.width, board.height), celda)
      val list3 = insertarValor(list2, list, celda)
      Minesweeper(Board(list2, board.width, board.height),GameOver(false), mines)
    }else Minesweeper(Board(list, board.width, board.height),GameOver(false), mines)
  }

  override def paresAleatorios(width: Width, height: Height, mines: Mines): List[(Abscisa, Ordenada)] = {
    val r = new scala.util.Random
    val list = List.range(1,mines.n+1).map( x => Tuple2( Abscisa( r.nextInt(width.n+1) ), Ordenada( r.nextInt(height.n+1)) ) )
    list
  }

  override def mostrarCeldasAdyacentes(board: Board, celda: Celda): List[Celda] = {

    val list2 = List(
      Celda(Abscisa(celda.abscisa.n+1), Ordenada(celda.ordenada.n+1), celda.valor, celda.marcada, celda.vista),
      Celda(Abscisa(celda.abscisa.n+1), Ordenada(celda.ordenada.n), celda.valor, celda.marcada, celda.vista),
      Celda(Abscisa(celda.abscisa.n+1), Ordenada(celda.ordenada.n-1), celda.valor, celda.marcada, celda.vista),
      Celda(Abscisa(celda.abscisa.n), Ordenada(celda.ordenada.n+1), celda.valor, celda.marcada, celda.vista),
      Celda(Abscisa(celda.abscisa.n), Ordenada(celda.ordenada.n-1), celda.valor, celda.marcada, celda.vista),
      Celda(Abscisa(celda.abscisa.n-1), Ordenada(celda.ordenada.n+1), celda.valor, celda.marcada, celda.vista),
      Celda(Abscisa(celda.abscisa.n-1), Ordenada(celda.ordenada.n), celda.valor, celda.marcada, celda.vista),
      Celda(Abscisa(celda.abscisa.n-1), Ordenada(celda.ordenada.n-1), celda.valor, celda.marcada, celda.vista)
    )
    val list3 = List(celda)
      list2.foreach( x => {
        if(isDentroRango(x, board.width, board.height)){
          list3:+x
        }
      })
    list3.tail
  }

  override def isDentroRango(celda: Celda, width: Width, height: Height): Boolean = {
    if (celda.abscisa.n > 0 && celda.abscisa.n < width.n+1 && celda.ordenada.n > 0 && celda.ordenada.n < height.n+1 ){
      true
    }else false
  }

  override def insertarValor(listAdyacentes: List[Celda], list: List[Celda], celda: Celda): List[Celda] = {
    val size = listAdyacentes.size
    val list1 = list.map(x => {
      if (celda.abscisa == x.abscisa && celda.ordenada == x.ordenada){
        Celda(x.abscisa, x.ordenada, Valor(size.toString), x.marcada, Vista(true))
      }else x
    })
    list1
  }

  override def juegoPerdido(minesweeper: Minesweeper): Boolean = {
    val list1 = minesweeper.board.matrix
    var suma = 0
    list1.foreach(x => {
      if ( x.valor.value == "*" && x.vista.view){
        suma = suma + 1
      }else
        suma = suma + 0
    })
    if(suma == 1 ){
      true
    }else false
  }

  override def juegoTerminado(minesweeper: Minesweeper): Boolean = {
    val list1 = minesweeper.board.matrix
    var suma = 0
    list1.foreach(x => {
      if ( x.marcada.m ){
        suma = suma + 1
      }else
        suma = suma + 0
    })
    if(suma == minesweeper.mines.n ){
      true
    }else false
  }

}

object InterpretacionServicioMinesweeper extends InterpretacionServicioMinesweeper