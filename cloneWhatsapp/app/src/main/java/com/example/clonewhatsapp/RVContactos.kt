package com.example.clonewhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class RVContactos : AppCompatActivity(), AdaptadorContactos.OnContactoClickListener {

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
            recyclerView,
            this
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    override fun onContactoClick(contacto: Contacto) {
        // Cuando se haga clic en un contacto, inicia la actividad RVChat y pasa el objeto Contacto como extra
        val intent = Intent(this, RVChat::class.java)
        intent.putExtra("CONTACTO_SELECCIONADO", contacto)
        startActivity(intent)
    }

}


