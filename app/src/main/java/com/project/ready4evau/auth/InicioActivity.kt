package com.project.ready4evau.auth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.MainActivity
import com.project.ready4evau.R

class InicioActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        auth = Firebase.auth
        var btn_Inicio = findViewById<Button>(R.id.btn_Registrarse)
        var tv_Registrate = findViewById<TextView>(R.id.tv_Registrate)
        var tv_Olvidar = findViewById<TextView>(R.id.tv_Olvidar)

        btn_Inicio.setOnClickListener{
            iniciarSesion()
        }

        tv_Registrate.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        tv_Olvidar.setOnClickListener{
            val intent = Intent(this, RecuperarPassword::class.java)
            startActivity(intent)
        }

    }

    private fun iniciarSesion(){
        var et_Correo = findViewById<EditText>(R.id.et_Correo)
        var et_Contrasena = findViewById<EditText>(R.id.et_Contrasena)
        var email = et_Correo.text.toString()
        var password = et_Contrasena.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    irVerificacion(task.result.user?.email.toString())
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Correo y/o contraseña incorrectos.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
    fun irVerificacion(email: String){

        val intent = Intent(this, Verificar::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent)
    }

}