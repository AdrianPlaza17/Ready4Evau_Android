package com.example.ready4evau.database

import java.io.Serializable

class PreguntaModel(val id_Pregunta : Int, val enunciado: String,   val solucion: String, val asignatura: String, val tema: String,
                    val numeroEnunciados: Int)


class ExamenModel(val id_Examen : Int, var pregunta1: String, var pregunta2: String, var pregunta3: String, var pregunta4: String,
                  var pregunta5: String, var nota1: Float, var nota2: Float, var nota3: Float, var nota4: Float, var nota5: Float,
                  val asignatura: String, val tema: String, var nota: String, var fecha: String, var user: String): Serializable

class ExamenAdapterModel(val id_Examen : Int, val asignatura: String, val tema: String, var nota: String, var fecha: String, var pregunta1: String, var pregunta2: String, var pregunta3: String, var pregunta4: String,
                         var pregunta5: String): Serializable
