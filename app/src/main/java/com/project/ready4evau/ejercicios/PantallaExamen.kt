package com.project.ready4evau.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.ready4evau.database.ExamenModel
import com.example.ready4evau.database.PreguntaModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.project.ready4evau.R
import com.squareup.picasso.Picasso

class PantallaExamen : AppCompatActivity() {
    lateinit var preguntaHelper : PreguntasDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_examen)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        preguntaHelper = PreguntasDBHelper(this)

        //Bundle
        val bundle = intent.extras
        var examen = bundle?.getSerializable("examen") as ExamenModel
        var n_ejercicio = bundle?.getInt("ejercicio")

        //Animaciones
        val anim = AnimationUtils.loadAnimation(this, R.anim.pulse)

        //Elementos interfaz
        val tv_ejercicio = findViewById<TextView>(R.id.tv_EjercicioExamen)

        val btn_atras = findViewById<Button>(R.id.btn_atras)
        val btn_adelante = findViewById<Button>(R.id.btn_adelante)
        val btn_solucion = findViewById<Button>(R.id.btn_SolucionExamen)
        val btn_responder = findViewById<ImageButton>(R.id.btn_ResponderPregunta)
        val btn_GuardarExamen = findViewById<Button>(R.id.btn_GuardarExamen)

        val cv_Foto = findViewById<CardView>(R.id.cv_Foto)


        btn_GuardarExamen.setOnClickListener{
            alertdialogAlertGuardarExamen(examen)
        }

        val iv_Pregunta = findViewById<ImageView>(R.id.iv_Pregunta)

        val ll_apartados = findViewById<LinearLayout>(R.id.ll_Apartados)
        val cb_a = findViewById<CheckBox>(R.id.cb_a)
        val cb_b = findViewById<CheckBox>(R.id.cb_b)
        val cb_c = findViewById<CheckBox>(R.id.cb_c)
        val cb_d = findViewById<CheckBox>(R.id.cb_d)

        val tv_preguntaRespondida = findViewById<TextView>(R.id.tv_PreguntaRespondida)

        val id_pregunta: String

        val pregunta: PreguntaModel




        when (n_ejercicio) {
            1 -> {
                tv_ejercicio.text = "Ejercicio 1"

                id_pregunta = examen.pregunta1
                btn_atras.visibility = View.INVISIBLE

                btn_adelante.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 2)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }

                pregunta = preguntaHelper.preguntaPorId(id_pregunta.toInt())
                Picasso.get().load(pregunta.enunciado).into(iv_Pregunta)

                //Cambia entre  solucion y enunciado
                val btn_Solucion= findViewById<Button>(R.id.btn_SolucionExamen)
                var cont:Int = 0

                btn_Solucion.setOnClickListener(){
                    cont += 1
                    cv_Foto.startAnimation(anim)
                    if (cont % 2 != 0){
                        Picasso.get().load(pregunta.solucion).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Enunciado")

                    }else{
                        Picasso.get().load(pregunta.enunciado).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Solución")
                    }

                }

                when (pregunta.numeroEnunciados) {
                    1 -> {
                        cb_b.visibility = View.INVISIBLE
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    2 -> {
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    3 -> {
                        cb_d.visibility = View.INVISIBLE

                    }
                }


                btn_responder.setOnClickListener{

                    dialogAlertResponderPregunta(examen, pregunta, n_ejercicio)

                }

                if (!examen.nota1.equals(0f)){
                    tv_preguntaRespondida.visibility = View.VISIBLE
                    ll_apartados.visibility = View.GONE
                    btn_responder.visibility = View.GONE
                }

            }
            2 -> {
                tv_ejercicio.text = "Ejercicio 2"

                id_pregunta = examen.pregunta2

                btn_atras.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 1)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                }

                btn_adelante.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 3)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }

                pregunta = preguntaHelper.preguntaPorId(id_pregunta.toInt())
                Picasso.get().load(pregunta.enunciado).into(iv_Pregunta)

                //Cambia entre  solucion y enunciado
                val btn_Solucion= findViewById<Button>(R.id.btn_SolucionExamen)
                var cont:Int = 0

                btn_Solucion.setOnClickListener(){
                    cont += 1
                    cv_Foto.startAnimation(anim)
                    if (cont % 2 != 0){
                        Picasso.get().load(pregunta.solucion).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Enunciado")

                    }else{
                        Picasso.get().load(pregunta.enunciado).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Solución")
                    }

                }

                when (pregunta.numeroEnunciados) {
                    1 -> {
                        cb_b.visibility = View.INVISIBLE
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    2 -> {
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    3 -> {
                        cb_d.visibility = View.INVISIBLE

                    }
                }
                btn_responder.setOnClickListener{
                    dialogAlertResponderPregunta(examen, pregunta, n_ejercicio)
                }

                if (!examen.nota2.equals(0f)){
                    tv_preguntaRespondida.visibility = View.VISIBLE
                    ll_apartados.visibility = View.GONE
                    btn_responder.visibility = View.GONE
                }

            }
            3 -> {
                tv_ejercicio.text = "Ejercicio 3"

                id_pregunta = examen.pregunta3

                btn_atras.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 2)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                }

                btn_adelante.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 4)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }

                pregunta = preguntaHelper.preguntaPorId(id_pregunta.toInt())
                Picasso.get().load(pregunta.enunciado).into(iv_Pregunta)

                //Cambia entre  solucion y enunciado
                val btn_Solucion= findViewById<Button>(R.id.btn_SolucionExamen)
                var cont:Int = 0

                btn_Solucion.setOnClickListener(){
                    cont += 1
                    cv_Foto.startAnimation(anim)
                    if (cont % 2 != 0){
                        Picasso.get().load(pregunta.solucion).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Enunciado")

                    }else{
                        Picasso.get().load(pregunta.enunciado).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Solución")
                    }

                }

                when (pregunta.numeroEnunciados) {
                    1 -> {
                        cb_b.visibility = View.INVISIBLE
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    2 -> {
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    3 -> {
                        cb_d.visibility = View.INVISIBLE

                    }
                }

                btn_responder.setOnClickListener{
                    dialogAlertResponderPregunta(examen, pregunta, n_ejercicio)
                }

                if (!examen.nota3.equals(0f)){
                    tv_preguntaRespondida.visibility = View.VISIBLE
                    ll_apartados.visibility = View.GONE
                    btn_responder.visibility = View.GONE
                }

            }
            4 -> {
                tv_ejercicio.text = "Ejercicio 4"

                id_pregunta = examen.pregunta4

                btn_atras.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 3)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                }

                btn_adelante.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 5)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }

                pregunta = preguntaHelper.preguntaPorId(id_pregunta.toInt())
                Picasso.get().load(pregunta.enunciado).into(iv_Pregunta)

                //Cambia entre  solucion y enunciado
                val btn_Solucion= findViewById<Button>(R.id.btn_SolucionExamen)
                var cont:Int = 0

                btn_Solucion.setOnClickListener(){
                    cont += 1
                    cv_Foto.startAnimation(anim)
                    if (cont % 2 != 0){
                        Picasso.get().load(pregunta.solucion).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Enunciado")

                    }else{
                        Picasso.get().load(pregunta.enunciado).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Solución")
                    }

                }

                when (pregunta.numeroEnunciados) {
                    1 -> {
                        cb_b.visibility = View.INVISIBLE
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    2 -> {
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    3 -> {
                        cb_d.visibility = View.INVISIBLE

                    }
                }

                btn_responder.setOnClickListener{
                    dialogAlertResponderPregunta(examen, pregunta, n_ejercicio)
                }

                if (!examen.nota4.equals(0f)){
                    tv_preguntaRespondida.visibility = View.VISIBLE
                    ll_apartados.visibility = View.GONE
                    btn_responder.visibility = View.GONE
                }

            }
            5 -> {
                tv_ejercicio.text = "Ejercicio 5"

                id_pregunta = examen.pregunta5
                btn_adelante.visibility = View.INVISIBLE
                btn_atras.setOnClickListener {
                    val intent = Intent(this, PantallaExamen::class.java)
                        .putExtra("examen", examen)
                        .putExtra("ejercicio", 4)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                }

                pregunta = preguntaHelper.preguntaPorId(id_pregunta.toInt())
                Picasso.get().load(pregunta.enunciado).into(iv_Pregunta)

                //Cambia entre  solucion y enunciado
                val btn_Solucion= findViewById<Button>(R.id.btn_SolucionExamen)
                var cont:Int = 0

                btn_Solucion.setOnClickListener(){
                    cont += 1
                    cv_Foto.startAnimation(anim)
                    if (cont % 2 != 0){
                        Picasso.get().load(pregunta.solucion).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Enunciado")

                    }else{
                        Picasso.get().load(pregunta.enunciado).into(iv_Pregunta);
                        btn_Solucion.setText("Ver Solución")
                    }

                }

                when (pregunta.numeroEnunciados) {
                    1 -> {
                        cb_b.visibility = View.INVISIBLE
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    2 -> {
                        cb_c.visibility = View.INVISIBLE
                        cb_d.visibility = View.INVISIBLE
                    }
                    3 -> {
                        cb_d.visibility = View.INVISIBLE

                    }
                }

                btn_responder.setOnClickListener{
                    dialogAlertResponderPregunta(examen, pregunta, n_ejercicio)
                }

                if (!examen.nota5.equals(0f)){
                    tv_preguntaRespondida.visibility = View.VISIBLE
                    ll_apartados.visibility = View.GONE
                    btn_responder.visibility = View.GONE
                }
            }
        }
    }

    fun calcularCheckBox(pregunta:PreguntaModel): Float {

        val cb_a = findViewById<CheckBox>(R.id.cb_a)
        val cb_b = findViewById<CheckBox>(R.id.cb_b)
        val cb_c = findViewById<CheckBox>(R.id.cb_c)
        val cb_d = findViewById<CheckBox>(R.id.cb_d)

        var cont: Int = 0
        var nota: Float = 0f


        when (pregunta.numeroEnunciados) {
            1 -> {
                cb_b.visibility = View.INVISIBLE
                cb_c.visibility = View.INVISIBLE
                cb_d.visibility = View.INVISIBLE

                if (cb_a.isChecked) {
                    nota = 2f
                } else {
                    nota = 0f
                }
            }
            2 -> {
                cb_c.visibility = View.INVISIBLE
                cb_d.visibility = View.INVISIBLE

                if (cb_a.isChecked) {
                    cont++
                }

                if (cb_b.isChecked) {
                    cont++
                }

                if (cont == 1) {
                    nota = 1f
                } else if (cont == 2) {
                    nota = 2f
                } else {
                    nota = 0f
                }

            }
            3 -> {
                cb_d.visibility = View.INVISIBLE

                if (cb_a.isChecked) {
                    cont++
                }

                if (cb_b.isChecked) {
                    cont++
                }

                if (cb_c.isChecked) {
                    cont++
                }

                if (cont == 1) {
                    nota = 0.67f
                } else if (cont == 2) {
                    nota = 1.33f
                } else if (cont == 3) {
                    nota = 2f
                } else {
                    nota = 0f
                }

            }
            4 -> {

                if (cb_a.isChecked) {
                    cont++
                }

                if (cb_b.isChecked) {
                    cont++
                }

                if (cb_c.isChecked) {
                    cont++
                }

                if (cb_d.isChecked) {
                    cont++
                }

                if (cont == 1) {
                    nota = 0.5f
                } else if (cont == 2) {
                    nota = 1f
                } else if (cont == 3) {
                    nota = 1.5f
                } else if (cont == 4) {
                    nota = 2f
                } else {
                    nota = 0f
                }


            }
        }


        return nota
    }

    fun dialogAlertResponderPregunta(examen: ExamenModel, pregunta: PreguntaModel, numeroPregunta: Int ){

        val ll_apartados = findViewById<LinearLayout>(R.id.ll_Apartados)
        val btn_responder = findViewById<ImageButton>(R.id.btn_ResponderPregunta)
        val tv_preguntaRespondida = findViewById<TextView>(R.id.tv_PreguntaRespondida)
        var nota: Float


        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle("Responder pregunta")
            .setMessage("Vas a responder la pregunta, ya no podras cambiar la pregunta. ¿Continuar?")
            .setPositiveButton("Confirmar") { dialog, which ->

                when (numeroPregunta) {
                    1 -> {
                        examen.nota1 = calcularCheckBox(pregunta)
                    }
                    2 -> {
                        examen.nota2 = calcularCheckBox(pregunta)
                    }
                    3 -> {
                        examen.nota3 = calcularCheckBox(pregunta)
                    }
                    4 -> {
                        examen.nota4 = calcularCheckBox(pregunta)
                    }
                    5 -> {
                        examen.nota5 = calcularCheckBox(pregunta)
                    }
                }

                tv_preguntaRespondida.visibility = View.VISIBLE
                ll_apartados.visibility = View.GONE
                btn_responder.visibility = View.GONE
                }
            .setNegativeButton("Cancelar") { dialog, which ->
            }
        alertDialogBuilder.show()
    }

    fun alertdialogAlertGuardarExamen(examen: ExamenModel){
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle("Terminar examen")
            .setMessage("Vas a terminar el examen. ¿Continuar?")
            .setPositiveButton("Confirmar") { dialog, which ->
                val intent = Intent(this, ResultadosExamen::class.java)
                    .putExtra("examen", examen)
                startActivity(intent)
            }
            .setNegativeButton("Cancelar") { dialog, which ->
            }
        alertDialogBuilder.show()

    }



}








