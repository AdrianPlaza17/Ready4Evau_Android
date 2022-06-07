package com.project.ready4evau

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.ready4evau.database.ExamenAdapterModel


internal class ExamAdapter(private var examList: List<ExamenAdapterModel>) :
    RecyclerView.Adapter<ExamAdapter.MyViewHolder>() {
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
    }
    override fun getItemCount(): Int {
        return examList.size
    }
}