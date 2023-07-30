package com.example.clonewhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RVChat : AppCompatActivity() {

    var contactoSeleccionado: Contacto? = null
    private var adaptador: AdaptadorMensajes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvchat)

        contactoSeleccionado = intent.getSerializableExtra("CONTACTO_SELECCIONADO") as? Contacto

        val mensajesChat = findViewById<RecyclerView>(R.id.rv_contactos)
        findViewById<ImageView>(R.id.atras).setOnClickListener{
            startActivity(Intent(this,RVContactos::class.java))
        }
        // Cargar la imagen del contacto utilizando Glide
        Glide.with(this)
            .load(contactoSeleccionado?.imagen)
            .into(findViewById<ImageView>(R.id.imagenContacto))
        findViewById<TextView>(R.id.contactoNombre).text = contactoSeleccionado?.nombre

        inicializar()

        //nuevo mensaje
        findViewById<ImageView>(R.id.enviar).setOnClickListener{
            val nuevoMensaje = findViewById<EditText>(R.id.mensajeNuevo).text.toString()
            val hora = obtenerHoraActual()
            contactoSeleccionado?.mensajes?.add(Mensajes(nuevoMensaje, true, hora))
            adaptador?.notifyDataSetChanged()
        }


    }
    fun obtenerHoraActual(): String {
        val formatoHora = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val horaActual = Date()
        return formatoHora.format(horaActual)
    }
    fun inicializar(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_contactos)
        adaptador = AdaptadorMensajes(
            this,
            contactoSeleccionado?.mensajes ?: ArrayList(),
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador!!.notifyDataSetChanged()
    }
}