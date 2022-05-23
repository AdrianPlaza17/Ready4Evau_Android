package com.project.ready4evau.auth


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.ready4evau.MainActivity
import com.project.ready4evau.R


class FeedInicioActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_inicio)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        setup()

        //Comprobar si hay una sesion iniciada
        session()


    }
    //Al dar al boton de atras cierra la app


    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)

        if (email !=null){
            irVerificacion(email)
        }
    }


    fun setup(){
        val btn_Inicio = findViewById<Button>(R.id.btn_Registrarse)
        val btn_Registro = findViewById<Button>(R.id.btn_Registro)
        val btn_Google = findViewById<Button>(R.id.btn_Google)
        val btn_Facebook = findViewById<Button>(R.id.btn_Facebook)

        btn_Inicio.setOnClickListener(){
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }

        btn_Registro.setOnClickListener(){
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btn_Google.setOnClickListener(){

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id1))
                .requestEmail()
                .build()

            val gsc = GoogleSignIn.getClient(this, gso)
            gsc.signOut()


            startActivityForResult(gsc.signInIntent,100)



        }

        btn_Facebook.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }


    fun irVerificacion(email: String){

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        auth = Firebase.auth
        if (requestCode == 100){

            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                auth.signInWithCredential(credential)


                irVerificacion(account.email ?:"")




            }catch (e: ApiException){
                Toast.makeText(baseContext, "Fallo al autenticar con Google.",
                    Toast.LENGTH_SHORT).show()
            }

        }
    }

    //Cierra la aplicacion
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}