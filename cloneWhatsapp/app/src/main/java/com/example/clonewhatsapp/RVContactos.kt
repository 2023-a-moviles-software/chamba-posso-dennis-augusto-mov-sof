package com.example.clonewhatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class RVContactos : AppCompatActivity() {

    val misContactos = BaseDatosMemoria.contactos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvcontactos)

        val contactosTotales = findViewById<RecyclerView>(R.id.rv_chats)
        inicializar()
    }

    fun inicializar(){
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_chats
        )
        val adaptador = AdaptadorContactos(
            this,
            misContactos,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
}