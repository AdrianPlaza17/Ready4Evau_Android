package com.project.ready4evau.ejercicios

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.ready4evau.database.ExamenModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.project.ready4evau.MainActivity
import com.project.ready4evau.R
import com.project.ready4evau.auth.FeedInicioActivity
import java.time.LocalDate

class ResultadosExamen : AppCompatActivity() {
    lateinit var preguntaHelper : PreguntasDBHelper

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_examen)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        preguntaHelper = PreguntasDBHelper(this)



        //Bundle
        val bundle = intent.extras
        var examen = bundle?.getSerializable("examen") as ExamenModel

        val tv_resultado1 = findViewById<TextView>(R.id.tv_resultado1)
        val tv_resultado2 = findViewById<TextView>(R.id.tv_resultado2)
        val tv_resultado3 = findViewById<TextView>(R.id.tv_resultado3)
        val tv_resultado4 = findViewById<TextView>(R.id.tv_resultado4)
        val tv_resultado5 = findViewById<TextView>(R.id.tv_resultado5)
        val tv_resultadoFinal = findViewById<TextView>(R.id.tv_resultadoFinal)

        tv_resultado1.text = examen.nota1.toString()
        tv_resultado2.text = examen.nota2.toString()
        tv_resultado3.text = examen.nota3.toString()
        tv_resultado4.text = examen.nota4.toString()
        tv_resultado5.text = examen.nota5.toString()

        colorNota(examen.nota1,tv_resultado1)
        colorNota(examen.nota2,tv_resultado2)
        colorNota(examen.nota3,tv_resultado3)
        colorNota(examen.nota4,tv_resultado4)
        colorNota(examen.nota5,tv_resultado5)

        val resultadoFinal = examen.nota1 + examen.nota2 + examen.nota3 + examen.nota4 + examen.nota5


        tv_resultadoFinal.text = resultadoFinal.toString()
        colorNotaFinal(resultadoFinal,tv_resultadoFinal)

        val fechaHoy = LocalDate.now().toString()

        examen.fecha=fechaHoy
        examen.nota=resultadoFinal.toString()
        ponerNota(examen)

        val btn_BorrarExamen = findViewById<Button>(R.id.btn_BorrarExamen)
        val btn_Terminar = findViewById<Button>(R.id.btn_Terminar)

        btn_BorrarExamen.setOnClickListener{
            borrarExamen()
        }
        btn_Terminar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    @SuppressLint("ResourceAsColor")
    fun colorNota(nota:Float, tv_resultado: TextView){
        if (nota==2f){
            tv_resultado.setTextColor(resources.getColor(R.color.success))
        }
        if (nota<1f){
            tv_resultado.setTextColor(resources.getColor(R.color.danger))
        }
    }
    fun colorNotaFinal(nota:Float, tv_resultado: TextView){
        if (nota>=8f){
            tv_resultado.setTextColor(resources.getColor(R.color.success))
        }
        if (nota<5f){
            tv_resultado.setTextColor(resources.getColor(R.color.danger))
        }
    }

    fun ponerNota(examenModel: ExamenModel){
        preguntaHelper.cambiarNotaUltimoExamen(examenModel.nota)
    }
    fun borrarExamen(){
        try {
            val alertDialogBuilder = AlertDialog.Builder(this)
                .setTitle("Borrar examen")
                .setMessage("Se va a eliminar el examen actual. ¿Continuar?")
                .setPositiveButton("Confirmar") { dialog, which ->
                    preguntaHelper.borrarUltimoExamen()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Examen borrado",
                        Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancelar") { dialog, which ->
                }
            alertDialogBuilder.show()

        }catch (e : SQLiteException){

        }


    }
}