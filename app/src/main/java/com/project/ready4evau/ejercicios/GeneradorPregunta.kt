package com.project.ready4evau.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.ready4evau.database.PreguntasDBHelper
import com.project.ready4evau.R
import com.squareup.picasso.Picasso


class GeneradorPregunta : AppCompatActivity() {
    lateinit var preguntaHelper : PreguntasDBHelper

    var p_IdPregunta: Int = 0
    var p_Enunciado: String = ""
    var p_Solucion: String = ""
    var p_Asignatura: String = ""
    var p_Tema: String = ""
    var p_NumeroEnunciados:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generador_pregunta)


        preguntaHelper= PreguntasDBHelper(this)
        //Recoge los parametros de las preguntas
        val bundle = intent.extras
        val asignatura = bundle?.getString("Asignatura")
        val tema = bundle?.getString("Tema")

        //Elementos de la interfaz
        val tv_Asignatura = findViewById<TextView>(R.id.tv_Asignatura)
        val tv_Tema = findViewById<TextView>(R.id.tv_Tema)
        val iv_Pregunta = findViewById<ImageView>(R.id.iv_Pregunta)
        val btn_NuevaPregunta =findViewById<TextView>(R.id.btn_NuevaPregunta)
        val btn_Solucion =findViewById<Button>(R.id.btn_Solucion)





        val arrayId = preguntaHelper.listaId(asignatura?:"",tema?:"")

        val numero = arrayId.random()

        //Elementos de pregunta
        val pregunta = preguntaHelper.preguntaPorId(numero)
            p_IdPregunta = pregunta.id_Pregunta
            p_Enunciado = pregunta.enunciado
            p_Solucion= pregunta.solucion
            p_Asignatura = pregunta.asignatura
            p_Tema = pregunta.tema
            p_NumeroEnunciados = pregunta.numeroEnunciados


        //Asignar textos
        tv_Asignatura.text=p_Asignatura
        tv_Tema.text=p_IdPregunta.toString()


        Picasso.get().load(p_Enunciado).into(iv_Pregunta);
        arrayId.remove(numero)
        var cont = 0

        val cv_Foto = findViewById<CardView>(R.id.cv_Foto)

        val anim = AnimationUtils.loadAnimation(this, R.anim.pulse)
        //Cambia la pregunta
        btn_NuevaPregunta.setOnClickListener{
            val numero = arrayId.random()
            val pregunta = preguntaHelper.preguntaPorId(numero)
                p_IdPregunta = pregunta.id_Pregunta
                p_Enunciado = pregunta.enunciado
                p_Solucion= pregunta.solucion
                p_Asignatura = pregunta.asignatura
                p_Tema = pregunta.tema
                p_NumeroEnunciados = pregunta.numeroEnunciados

            findViewById<TextView>(R.id.tv_Asignatura).text=p_Asignatura
            findViewById<TextView>(R.id.tv_Tema).text=p_IdPregunta.toString()


            Picasso.get().load(pregunta.enunciado).into(iv_Pregunta);
            btn_Solucion.setText("Ver Solucion")

            arrayId.remove(numero)

            cont=0


        }


        //Cambia entre  solucion y enunciado
        btn_Solucion.setOnClickListener(){
            cont += 1
            cv_Foto.startAnimation(anim)

            if (cont % 2 != 0){
                Picasso.get().load(p_Solucion).into(iv_Pregunta);
                btn_Solucion.setText("Ver Enunciado")

            }else{
                Picasso.get().load(p_Enunciado).into(iv_Pregunta);
                btn_Solucion.setText("Ver Solución")
            }

        }


    }


}