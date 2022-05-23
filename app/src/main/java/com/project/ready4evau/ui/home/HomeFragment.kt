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

        val btn_matematicas = binding.btnMatematicas
        val btn_quimica = binding.btnQuimica
        val btn_fisica = binding.btnFisica

        btn_matematicas.setOnClickListener(){
            (activity as MainActivity).irAsignatura("Matematicas")
        }
        btn_quimica.setOnClickListener(){
            (activity as MainActivity).irAsignatura("Quimica")
        }
        btn_fisica.setOnClickListener(){
            (activity as MainActivity).irAsignatura("Fisica")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}