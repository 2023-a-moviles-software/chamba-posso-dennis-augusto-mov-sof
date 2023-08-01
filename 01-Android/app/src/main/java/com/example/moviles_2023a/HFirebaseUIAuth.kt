package com.example.moviles_2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class HFirebaseUIAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        btnLogin.setOnClickListener{
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
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.setOnClickListener{seDeslogeo()}

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
        val btnLogin: Button = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout: Button = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.visibility = View.VISIBLE
        btnLogin.visibility = View.INVISIBLE
        if(res.isNewUser == true){
            //registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun seDeslogeo(){
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.visibility = View.INVISIBLE
        btnLogin.visibility = View.VISIBLE
        FirebaseAuth.getInstance().signOut()
    }
}