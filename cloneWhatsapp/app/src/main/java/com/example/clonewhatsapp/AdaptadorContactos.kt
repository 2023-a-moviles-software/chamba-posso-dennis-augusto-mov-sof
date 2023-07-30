package com.example.clonewhatsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorContactos (
    private val interfazMain: RVContactos,
    private val contactos: ArrayList<Contacto>,
    private val recyclerView: RecyclerView,
    private val onContactoClickListener: OnContactoClickListener
): RecyclerView.Adapter<AdaptadorContactos.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val fotoContacto: ImageView
        val ultimoMensaje: TextView
        val nombreDeContacto: TextView

        init{
            fotoContacto = view.findViewById(R.id.img_perfilContacto)
            ultimoMensaje = view.findViewById(R.id.ultimoMensaje)
            nombreDeContacto = view.findViewById(R.id.nombreContacto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.activity_rv_vista_contacto,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.contactos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contactoActual = this.contactos[position]
        //holder.fotoContacto.setImageResource()
        holder.ultimoMensaje.text = contactoActual.mensajes.last().contenido
        holder.nombreDeContacto.text = contactoActual.nombre

        holder.itemView.setOnClickListener{
            onContactoClickListener.onContactoClick(contactoActual)
        }

    }
    interface OnContactoClickListener{
        fun onContactoClick(contacto: Contacto)
    }
}