package com.project.ready4evau.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ready4evau.database.ExamenAdapterModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.ExamAdapter
import com.project.ready4evau.MainActivity
import com.project.ready4evau.R


import com.project.ready4evau.databinding.FragmentDashboardBinding



class DashboardFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var userID :String
    private lateinit var al_examen: ArrayList<ExamenAdapterModel>

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    lateinit var PreguntasDBHelper: PreguntasDBHelper
    private lateinit var examAdapter: ExamAdapter
    private val examList = ArrayList<ExamenAdapterModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        PreguntasDBHelper = PreguntasDBHelper(requireContext())

        auth = Firebase.auth

        selectExamen()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun selectExamen(){

        userID = auth.currentUser?.uid.toString()
        al_examen = PreguntasDBHelper.selectAllExams(userID)

        val recyclerView: RecyclerView = binding.reciclerViewExamenes


        examAdapter = ExamAdapter(al_examen, {index -> borrarExamenLista(index)})

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = examAdapter



    }

    fun borrarExamenLista(position: Int){
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Borrar examen")
            .setMessage("Se va a eliminar el examen. ¿Continuar?")
            .setPositiveButton("Confirmar") { dialog, which ->
                al_examen.removeAt(position)
                examAdapter.setItems(al_examen)
            }
            .setNegativeButton("Cancelar") { dialog, which ->
            }
        alertDialogBuilder.show()



    }
}