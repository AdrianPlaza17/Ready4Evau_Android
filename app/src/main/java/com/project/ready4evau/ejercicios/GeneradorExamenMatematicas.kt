package com.project.ready4evau.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ready4evau.database.ExamenModel
import com.example.ready4evau.database.PreguntaModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.project.ready4evau.R
import com.project.ready4evau.asignaturas.Fisica
import java.time.LocalDate
import java.util.*

class GeneradorExamenMatematicas : AppCompatActivity() {
    lateinit var preguntaHelper : PreguntasDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generador_examen)

        preguntaHelper= PreguntasDBHelper(this)

        val btn_examinar = findViewById<Button>(R.id.btn_examinarse)


        btn_examinar.setOnClickListener{

            val id_pregunta_al = preguntaHelper.listaId("Matematicas")

            var examen = ExamenModel(0,"","","","","",0f,0f,0f,0f, 0f,"Matematicas","Todos","-",LocalDate.now().toString())

            var i = 1
            while (i <= 5){

                var id_pregunta = id_pregunta_al.random()
                id_pregunta_al.remove(id_pregunta)
                if (i==1){

                    examen.pregunta1 = id_pregunta.toString()
                }
                else if (i==2){
                    examen.pregunta2 = id_pregunta.toString()
                }
                else if (i==3){
                    examen.pregunta3 = id_pregunta.toString()
                }
                else if (i==4){
                    examen.pregunta4 = id_pregunta.toString()
                }
                else if (i==5){
                    examen.pregunta5 = id_pregunta.toString()
                }
                i++
            }


            preguntaHelper.crearExamen(examen)

            val intent =Intent(this, PantallaExamen::class.java)
                .putExtra("examen", examen)
                .putExtra("ejercicio", 1)
           startActivity(intent)


        }









    }
}