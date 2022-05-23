package com.project.ready4evau.auth

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.R

class RecuperarPassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_password)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        auth = Firebase.auth

        auth.useAppLanguage()

        val actionCodeSettings = actionCodeSettings {
            // URL you want to redirect back to. The domain (www.example.com) for this
            // URL must be whitelisted in the Firebase Console.
            url = "ready4evau.web.app"
            // This must be true
            handleCodeInApp = true
            setIOSBundleId("com.example.ios")
            setAndroidPackageName(
                "com.example.android",
                true, /* installIfNotAvailable */
                "12" /* minimumVersion */)
        }

        val et_CorreoRecuperar = findViewById<EditText>(R.id.et_CorreoRecuperar)

        val btn_CorreoRecuperar = findViewById<Button>(R.id.btn_Recuperar)

        btn_CorreoRecuperar.setOnClickListener{
            val mail = et_CorreoRecuperar.text.toString()
            auth.sendPasswordResetEmail(mail).addOnSuccessListener{
                Toast.makeText(baseContext, "Correo enviado.",
                    Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(baseContext, "Correo no enviado.",
                    Toast.LENGTH_SHORT).show()
            }
        }


    }
}