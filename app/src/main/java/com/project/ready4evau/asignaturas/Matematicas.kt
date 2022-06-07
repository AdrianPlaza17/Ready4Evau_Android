package com.project.ready4evau.asignaturas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.project.ready4evau.R
import com.project.ready4evau.ejercicios.GeneradorExamenMatematicas
import com.project.ready4evau.ejercicios.GeneradorPregunta
import java.time.LocalDate

class Matematicas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matematicas)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val btn_Matrices = findViewById<Button>(R.id.btn_Matrices)
        val btn_Determinantes = findViewById<Button>(R.id.btn_Determinantes)
        val btn_Geometria = findViewById<Button>(R.id.btn_Geometria)
        val btn_Limites = findViewById<Button>(R.id.btn_Limites)
        val btn_Derivadas = findViewById<Button>(R.id.btn_Derivadas)
        val btn_Integrales = findViewById<Button>(R.id.btn_Integrales)
        val btn_Probabilidad = findViewById<Button>(R.id.btn_Probabilidad)
        val btn_Fecha = findViewById<Button>(R.id.btn_FechaHoy)

        val hoy = LocalDate.now().toString()

        btn_Matrices.setOnClickListener{ generarPregunta("Matrices") }
        btn_Determinantes.setOnClickListener{ generarPregunta("Determinantes") }
        btn_Geometria.setOnClickListener{ generarPregunta("Geometria") }
        btn_Limites.setOnClickListener{ generarPregunta("Limites") }
        btn_Derivadas.setOnClickListener{ generarPregunta("Derivadas") }
        btn_Integrales.setOnClickListener{ generarPregunta("Integrales") }
        btn_Probabilidad.setOnClickListener{ generarPregunta("Probabilidad") }
        btn_Fecha.setOnClickListener{
            startActivity(Intent(this, GeneradorExamenMatematicas::class.java))
            Toast.makeText(baseContext, hoy,
                Toast.LENGTH_SHORT).show()
        }

    }

    fun generarPregunta(Tema:String){
        val intent = Intent(this, GeneradorPregunta::class.java).apply {
            putExtra("Asignatura", "Matematicas")
            putExtra("Tema", Tema)
        }
        startActivity(intent)
    }
    
}