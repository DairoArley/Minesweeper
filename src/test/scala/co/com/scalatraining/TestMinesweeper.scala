package co.com.scalatraining

import co.com.scalatraining.modelling.dominio.entidades.entidades._
import co.com.scalatraining.modelling.dominio.entidades.servicios.InterpretacionServicioMinesweeper
import org.scalatest.FunSuite
//import org.scalatest.FunSuite
//import org.scalatest._

class TestMinesweeper extends FunSuite {
  test("Un Minesweeper se puede crear") {
    val hola = InterpretacionServicioMinesweeper.paresAleatorios(Width(5),Height(2),Mines(3))
    assert(hola === List(List()) )
  }

  test("Random"){
    val hola = InterpretacionServicioMinesweeper.paresAleatorios(Width(5),Height(2),Mines(3))
    assert(hola === 30)
  }

  test("Las cosas estan"){
    val hola = InterpretacionServicioMinesweeper.construirBoard(Width(2), Height(2), Mines(1))
    assert(hola === Board(List(List(Celda(Abscisa(0),Ordenada(0),Valor('.'),Vista(false))),
      List(Celda(Abscisa(0),Ordenada(1),Valor('.'),Vista(false))), List(Celda(Abscisa(0),Ordenada(2),Valor('.'),Vista(false))),
      List(Celda(Abscisa(1),Ordenada(0),Valor('.'),Vista(false))), List(Celda(Abscisa(1),Ordenada(1),Valor('.'),Vista(false))),
      List(Celda(Abscisa(1),Ordenada(2),Valor('.'),Vista(false))), List(Celda(Abscisa(2),Ordenada(0),Valor('.'),Vista(false))),
    List(Celda(Abscisa(2),Ordenada(1),Valor('.'),Vista(false))), List(Celda(Abscisa(2),Ordenada(2),Valor('.'),Vista(false )))))   )
  }
  test("nada normal"){
    val list = List(1,2,3)
    val list1 = list.foldLeft (4) (_ + _)
    assert(list == list1)
  }
}
