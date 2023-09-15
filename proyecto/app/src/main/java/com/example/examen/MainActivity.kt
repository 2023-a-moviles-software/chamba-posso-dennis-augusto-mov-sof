package com.example.examen

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton = findViewById<Button>(R.id.btn_iniciar)
        boton.setOnClickListener{

            val providers = arrayListOf(
                //arreglo de providers para logearse
                AuthUI.IdpConfig.EmailBuilder().build()
            )
            //construimos el intent de login
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            //respuesta del intent de login
            respuestaLoginAuthUI.launch(signInIntent)
        }
    }

    //callback del intent de login
    private val respuestaLoginAuthUI = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){
            res: FirebaseAuthUIAuthenticationResult ->
        if(res.resultCode === RESULT_OK){
            if(res.idpResponse != null){
                //logica del negocio
                seLogeo(res.idpResponse!!)
            }
        }
    }
    fun seLogeo(res: IdpResponse){
        val btnLogin: Button = findViewById<Button>(R.id.btn_iniciar)

        btnLogin.visibility = View.INVISIBLE
        if(res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res)
        }
        irActividad(ListView_Docentes::class.java)
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){ /* usuario.email; usuario.phoneNumber; usuario.user.name;*/  }



    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)
        // this.startActivity()
    }
}