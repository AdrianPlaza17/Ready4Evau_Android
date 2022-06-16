package com.project.ready4evau

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ready4evau.database.ExamenAdapterModel
import com.example.ready4evau.database.ExamenModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.ejercicios.PantallaExamen
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


internal class ExamAdapter(var examList: List<ExamenAdapterModel>, val onClickDelete: (Int) -> Unit) :
    RecyclerView.Adapter<ExamAdapter.MyViewHolder>() {

    lateinit var preguntaHelper : PreguntasDBHelper
    lateinit var listExam: List<ExamenAdapterModel>
    private lateinit var auth: FirebaseAuth


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_asignatura: TextView = view.findViewById(R.id.tv_asignaturaAdapter)
        var tv_tema: TextView = view.findViewById(R.id.tv_temaAdapter)
        var tv_nota: TextView = view.findViewById(R.id.tv_notaAdapter)
        var tv_fecha: TextView = view.findViewById(R.id.tv_fechaAdapter)
        var ib_rehacer: ImageButton = view.findViewById(R.id.ib_rehacerAdapter)
        var ib_borrar: ImageButton = view.findViewById(R.id.ib_borrarAdapter)



    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.exam, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = examList[position]
        holder.tv_asignatura.text = item.asignatura
        holder.tv_tema.text = item.tema
        holder.tv_fecha.text = item.fecha
        holder.tv_nota.text = item.nota

        var context = holder.tv_nota.context
        preguntaHelper = PreguntasDBHelper(context)
        auth = Firebase.auth

        holder.ib_borrar.setOnClickListener(){
            onClickDelete(position)
            preguntaHelper.borrarExamen(item.id_Examen)
        }

        holder.ib_rehacer.setOnClickListener{
            val alertbox: AlertDialog.Builder = AlertDialog.Builder(context)
            alertbox.setMessage("Vas a repetir el examen ¿Continuar?")
            alertbox.setTitle("Repetir examen")
                .setPositiveButton("Confirmar") { dialog, which ->
                    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
                    val currentDate = sdf.format(Date())

                    var examenRepetido: ExamenModel = ExamenModel(0, item.pregunta1, item.pregunta1, item.pregunta1, item.pregunta1, item.pregunta1,0f,0f,0f,0f, 0f,item.asignatura,item.tema,"-",currentDate,auth.currentUser?.uid.toString())
                    preguntaHelper.crearExamen(examenRepetido)


                    val intent = Intent(context, PantallaExamen::class.java)
                        .putExtra("examen", examenRepetido)
                        .putExtra("ejercicio", 1)
                    context.startActivity(intent)
                }
                .setNegativeButton("Cancelar") { dialog, which ->
                }
            alertbox.show()
        }
    }
    override fun getItemCount(): Int {
        return examList.size
    }

    fun setItems(items: ArrayList<ExamenAdapterModel>){
        examList = items
        notifyDataSetChanged()
    }


}