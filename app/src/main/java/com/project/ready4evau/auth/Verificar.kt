package com.project.ready4evau.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.MainActivity
import com.project.ready4evau.R

class Verificar : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        auth = Firebase.auth
        val user = auth.currentUser
        val verificado = user?.isEmailVerified
        if (verificado==true){
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("email", user.email)
            }
            startActivity(intent)
        }

        val btn_VerificarEmail = findViewById<Button>(R.id.btn_VerificarEmail)
        btn_VerificarEmail.setOnClickListener{
            user?.sendEmailVerification()
            Toast.makeText(baseContext, "Se ha enviado email de verificación.",
                Toast.LENGTH_SHORT).show()
            btn_VerificarEmail.isEnabled = false
        }
    }
}