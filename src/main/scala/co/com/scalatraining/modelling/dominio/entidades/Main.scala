package co.com.scalatraining.modelling.dominio.entidades

import java.io.{BufferedReader, IOException, InputStreamReader}

import co.com.scalatraining.modelling.dominio.entidades.entidades.{Height, Mines, Width}
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
    val t = principal.construirBoard(Width(width), Height(height), Mines(mines))
  do{
    principal.imprimirBoard(t)
    print("Ingrese la celda que quiere seleccionar: ")
    print("Ingrese la coordenada en x: ")
    val x = scala.io.StdIn.readInt()
    print("Ingrese la coordenada en y: ")
    val y = scala.io.StdIn.readInt()


  }while()
}


