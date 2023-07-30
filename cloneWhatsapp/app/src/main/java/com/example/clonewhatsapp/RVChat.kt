package com.example.clonewhatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVChat : AppCompatActivity() {

    var contactoSeleccionado: Contacto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvchat)
        contactoSeleccionado = intent.getSerializableExtra("CONTACTO_SELECCIONADO") as? Contacto
        val mensajesChat = findViewById<RecyclerView>(R.id.rv_contactos)
        findViewById<TextView>(R.id.contactoNombre).text = contactoSeleccionado?.nombre
        inicializar()
    }

    fun inicializar(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_contactos)
        val adaptador = AdaptadorMensajes(
            this,
            contactoSeleccionado?.mensajes ?: ArrayList(),
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
}