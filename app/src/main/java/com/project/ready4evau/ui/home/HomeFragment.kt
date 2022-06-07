package com.project.ready4evau.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.ready4evau.MainActivity

import com.project.ready4evau.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btn_matematicasEjercicio = binding.btnMatematicasEjercicio
        val btn_quimicaEjercicio = binding.btnQuimicaEjercicio
        val btn_fisicaEjercicio = binding.btnFisicaEjercicio

        val btn_matematicasExamen = binding.btnMatematicasExamen
        val btn_quimicaExamen = binding.btnQuimicaExamen
        val btn_fisicaExamen = binding.btnFisicaExamen

        btn_matematicasEjercicio.setOnClickListener(){
            (activity as MainActivity).irAsignatura("Matematicas")
        }
        btn_quimicaEjercicio.setOnClickListener(){
            (activity as MainActivity).irAsignatura("Quimica")
        }
        btn_fisicaEjercicio.setOnClickListener(){
            (activity as MainActivity).irAsignatura("Fisica")
        }

        btn_matematicasExamen.setOnClickListener(){
            (activity as MainActivity).irAsignatura("MatematicasExamen")
        }
        btn_quimicaExamen.setOnClickListener(){
            (activity as MainActivity).irAsignatura("QuimicaExamen")
        }
        btn_fisicaExamen.setOnClickListener(){
            (activity as MainActivity).irAsignatura("FisicaExamen")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}