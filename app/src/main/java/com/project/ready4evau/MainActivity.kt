package com.project.ready4evau

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ready4evau.database.PreguntasDBHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.auth.FeedInicioActivity
import com.project.ready4evau.databinding.ActivityMainBinding
import com.project.ready4evau.ejercicios.GeneradorExamenMatematicas
import com.project.ready4evau.ejercicios.ResultadosExamen


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    lateinit var preguntaHelper : PreguntasDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        preguntaHelper= PreguntasDBHelper(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        val ib_logout = findViewById<ImageButton>(R.id.ib_logout)
        ib_logout.setOnClickListener(){
            cerrarSesion()
        }

        val bundle = intent.extras
        val email = bundle?.getString("email")


        guardarDatos(email?: "")

    }

    fun cerrarSesion(): Unit {
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle("Cerrar sesión")
            .setMessage("Se va a cerrar la sesión en el dispositivo. ¿Continuar?")
            .setPositiveButton("Confirmar") { dialog, which ->
                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                prefs.clear()
                prefs.commit()

                auth.signOut()

                val intent = Intent(this, FeedInicioActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Cancelar") { dialog, which ->
            }
        alertDialogBuilder.show()


    }

    fun guardarDatos(email: String): Unit {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.apply()
    }

    fun irAsignatura(asignatura: String){
        when (asignatura) {
            "Matematicas" -> startActivity(Intent(this, com.project.ready4evau.asignaturas.Matematicas::class.java))
            "Fisica"-> startActivity(Intent(this, com.project.ready4evau.asignaturas.Fisica::class.java))
            "Quimica"->startActivity(Intent(this, com.project.ready4evau.asignaturas.Quimica::class.java))
            "MatematicasExamen" -> startActivity(Intent(this, GeneradorExamenMatematicas::class.java))
            "FisicaExamen" -> startActivity(Intent(this, GeneradorExamenMatematicas::class.java))
            "QuimicaExamen" -> startActivity(Intent(this, GeneradorExamenMatematicas::class.java))

        }
    }

    //Cierra la aplicacion
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }



}