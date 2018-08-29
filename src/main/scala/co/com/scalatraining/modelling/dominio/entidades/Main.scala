package co.com.scalatraining.modelling.dominio.entidades

import java.io.{BufferedReader, IOException, InputStreamReader}

import co.com.scalatraining.modelling.dominio.entidades.entidades.{Abscisa, _}
import co.com.scalatraining.modelling.dominio.entidades.servicios.InterpretacionServicioMinesweeper

object Main extends App {

///
    val principal = InterpretacionServicioMinesweeper
    print("Ingrese las dimensiones del Board y las minas\n")
    print("Ancho: ")
    val width = scala.io.StdIn.readInt()
    print("Alto: ")
    val height = scala.io.StdIn.readInt()
    print("Número de minas: ")
    val mines =scala.io.StdIn.readInt()
    print("\n")
    var t = principal.construirMinesweeper(Width(width), Height(height), Mines(mines))
    var minesweeper = t
  do{
    principal.imprimirMinesweeper(t)
    println("Ingrese la celda y la acción que quiere realizar: ")
    print("Ingrese la coordenada en x: ")
    val x = scala.io.StdIn.readInt()
    print("Ingrese la coordenada en y: ")
    val y = scala.io.StdIn.readInt()
    print("Ingrese la acción que desea realizar: ")
    val accion = scala.io.StdIn.readLine()
    minesweeper = principal.realizarAccion(minesweeper.board, Ordenada(y),Abscisa(x),Accion(accion), minesweeper.mines  )



  }while(principal.juegoTerminado(minesweeper) && principal.juegoPerdido(minesweeper) )

  if(principal.juegoTerminado(minesweeper)){
    println("You win")
  }else
    println("You loose")
  print("Fin del juego")
}


