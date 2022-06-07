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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ready4evau.database.ExamenAdapterModel
import com.example.ready4evau.database.PreguntasDBHelper
import com.project.ready4evau.ExamAdapter
import com.project.ready4evau.R


import com.project.ready4evau.databinding.FragmentDashboardBinding



class DashboardFragment : Fragment() {


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

        selectExamen()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun selectExamen(){


        val al_examen = PreguntasDBHelper.selectAllExams()



        val recyclerView: RecyclerView = binding.reciclerViewExamenes

        examAdapter = ExamAdapter(al_examen)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = examAdapter

    }
}