package com.project.ready4evau.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.ready4evau.database.ExamenModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.R
import java.text.SimpleDateFormat
import java.util.*

class GeneradorExamenMatematicas : AppCompatActivity() {

    lateinit var preguntaHelper : PreguntasDBHelper
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generador_examen)

        preguntaHelper= PreguntasDBHelper(this)
        auth = Firebase.auth
        val btn_examinarTodos = findViewById<Button>(R.id.btn_examinarseTodos)
        val btn_examinarMatrices = findViewById<Button>(R.id.btn_examinarseMatrices)
        val btn_examinarDerivadas = findViewById<Button>(R.id.btn_examinarseDerivadas)
        val btn_examinarProbabilidad = findViewById<Button>(R.id.btn_examinarseProbabilidad)
        val btn_examinarDeterminantes = findViewById<Button>(R.id.btn_examinarseDeterminantes)
        val btn_examinarIntegrales = findViewById<Button>(R.id.btn_examinarseIntegrales)
        val btn_examinarGeometria= findViewById<Button>(R.id.btn_examinarseGeometria)
        val btn_examinarLimites = findViewById<Button>(R.id.btn_examinarseLimites)


        btn_examinarTodos.setOnClickListener{
            hacerExamenTodos()
        }
        btn_examinarDerivadas.setOnClickListener(){hacerExamenAsignatura("Derivadas")}
        btn_examinarMatrices.setOnClickListener(){hacerExamenAsignatura("Matrices")}
        btn_examinarProbabilidad.setOnClickListener(){hacerExamenAsignatura("Probabilidad")}
        btn_examinarDeterminantes.setOnClickListener(){hacerExamenAsignatura("Determinantes")}
        btn_examinarIntegrales.setOnClickListener(){hacerExamenAsignatura("Integrales")}
        btn_examinarGeometria.setOnClickListener(){hacerExamenAsignatura("Geometria")}
        btn_examinarLimites.setOnClickListener(){hacerExamenAsignatura("Limites")}









    }
    fun hacerExamenTodos(){
        val id_pregunta_al = preguntaHelper.listaId("Matematicas")

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
        val currentDate = sdf.format(Date())


        var examen = ExamenModel(0,"","","","","",0f,0f,0f,0f, 0f,"Matematicas","Todos los temas","-",currentDate,auth.currentUser?.uid.toString())

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

        Toast.makeText(baseContext, currentDate,
            Toast.LENGTH_LONG).show()

        preguntaHelper.crearExamen(examen)

        val intent =Intent(this, PantallaExamen::class.java)
            .putExtra("examen", examen)
            .putExtra("ejercicio", 1)
        startActivity(intent)
    }
    fun hacerExamenAsignatura(tema : String){
        val id_pregunta_al = preguntaHelper.listaId("Matematicas", tema)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
        val currentDate = sdf.format(Date())


        var examen = ExamenModel(0,"","","","","",0f,0f,0f,0f, 0f,"Matematicas",tema,"-",currentDate,auth.currentUser?.uid.toString())

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