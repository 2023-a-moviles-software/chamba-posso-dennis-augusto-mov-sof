package com.example.clonewhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imagen = findViewById<ImageView>(R.id.imagen)
        val boton = findViewById<Button>(R.id.btn_iniciar)
        boton.setOnClickListener{
            imagen.setImageResource(R.drawable.despues)

            Handler().postDelayed({
                //irActividad(::class.java)
            }, 1000)
        }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)
        // this.startActivity()
    }
}