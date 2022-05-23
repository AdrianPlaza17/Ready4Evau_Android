package com.project.ready4evau.ui.dashboard

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


import com.project.ready4evau.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

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

        val btn_facebook: Button = binding.btnFacebook
        btn_facebook.setOnClickListener(){
            val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(("https://www.facebook.com/")))
            startActivity(intentWeb)
        }
        val btn_twitter: Button = binding.btnTwitter
        btn_twitter.setOnClickListener(){
            val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(("https://twitter.com/?lang=es")))
            startActivity(intentWeb)
        }

        val btn_camara: Button = binding.btnCamara
        btn_camara.setOnClickListener(){
            val intentcamara=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intentcamara)
        }
        val et_numero: EditText = binding.etNumero

        val btn_llamar: Button = binding.btnLlamar
        btn_llamar.setOnClickListener(){
            val llamada: Intent= Uri.parse("tel: +34"+et_numero.text.toString()).let { numero-> Intent(Intent.ACTION_DIAL, numero)}
            startActivity(llamada)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}