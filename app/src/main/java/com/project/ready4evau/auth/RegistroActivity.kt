package com.project.ready4evau.auth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.MainActivity
import com.project.ready4evau.R

class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        // Initialize Firebase Auth
        auth = Firebase.auth
        val user = auth.currentUser

        var btn_Registrarse = findViewById<Button>(R.id.btn_Registrarse)
        btn_Registrarse.setOnClickListener{
            createAccount()
        }
    }

    fun createAccount(){
        var et_Correo = findViewById<EditText>(R.id.et_Correo)
        var et_Contrasena = findViewById<EditText>(R.id.et_Contrasena)
        var et_ContrasenaVerificar = findViewById<EditText>(R.id.et_ContrasenaVerificar)
        var cb_PoliticaPrivacidad = findViewById<CheckBox>(R.id.cb_PoliticaPrivacidad)
        var email = et_Correo.text.toString()
        var password = et_Contrasena.text.toString()
        var passwordVerify = et_ContrasenaVerificar.text.toString()


        if (password.equals(passwordVerify) && cb_PoliticaPrivacidad.isChecked && email.isNotEmpty() && password.isNotEmpty()){

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Exito",Toast.LENGTH_SHORT).show()
                        irVerificacion(task.result.user?.email.toString())

                        user?.sendEmailVerification()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Datos erroneos.",
                            Toast.LENGTH_SHORT).show()
                        // updateUI(null)
                    }
                }
        }else if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(baseContext, "Rellena todos los campos.",Toast.LENGTH_SHORT).show()
        }else if(!password.equals(passwordVerify)){
            Toast.makeText(baseContext, "Las contraseñas no coinciden.",Toast.LENGTH_SHORT).show()
        }else if (!cb_PoliticaPrivacidad.isChecked){
            Toast.makeText(baseContext, "Acepta las politicas de privacidad.",Toast.LENGTH_SHORT).show()
        }

    }

    fun irVerificacion(email: String){

        val intent = Intent(this, Verificar::class.java).apply {
            putExtra("email", email)

        }
        startActivity(intent)
    }

    /*public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }*/


}