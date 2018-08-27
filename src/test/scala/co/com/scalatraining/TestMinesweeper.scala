package co.com.scalatraining

import co.com.scalatraining.modelling.dominio.entidades.entidades._
import co.com.scalatraining.modelling.dominio.entidades.servicios.InterpretacionServicioMinesweeper
import org.scalatest.FunSuite

class TestMinesweeper extends FunSuite {

  test("Las cosas estan"){
    val hola = InterpretacionServicioMinesweeper.construirBoard(Width(2), Height(2), Mines(1))
    assert(hola == Board(List(Celda(Abscisa(0),Ordenada(0),Valor('.'),Vista(false)),
      Celda(Abscisa(0),Ordenada(1),Valor('.'),Vista(false)),
      Celda(Abscisa(1),Ordenada(0),Valor('.'),Vista(false)),
      Celda(Abscisa(1),Ordenada(1),Valor('.'),Vista(false))),Width(2),Height(2)) )
  }

}
