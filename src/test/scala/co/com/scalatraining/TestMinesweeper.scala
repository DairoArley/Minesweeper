package co.com.scalatraining

import co.com.scalatraining.modelling.dominio.entidades.entidades._
import co.com.scalatraining.modelling.dominio.entidades.servicios.InterpretacionServicioMinesweeper
import org.scalatest.FunSuite

class TestMinesweeper extends FunSuite {

  test("Las cosas estan"){
    val hola = InterpretacionServicioMinesweeper.construirMinesweeper(Width(2), Height(2), Mines(1))

  }
  test ("Las cosas de la vida"){
    val nada = 3
    val si = nada.toChar
    assert(si === '3')
  }
  test("esta"){
    val hola = List(1,2,3,4)
    val tres = hola:+5
    assert(tres === List(1,2,3,4,5) )
    val trsss = tres.tail
    assert(trsss === List(2,3,4,5))
    assert(hola.size.toString === "4")
  }

}
