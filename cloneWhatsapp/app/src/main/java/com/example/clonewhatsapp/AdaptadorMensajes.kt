package com.example.clonewhatsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorMensajes (
    private val interfazChat: RVChat,
    private val mensajes: ArrayList<Mensajes>,
    private val recyclerView: RecyclerView,
    private var esRecivido: Int = 0
): RecyclerView.Adapter<AdaptadorMensajes.MyViewHolderMensajes>(){

    inner class MyViewHolderMensajes(view: View): RecyclerView.ViewHolder(view){
        val mensaje: TextView
        val hora: TextView

        init{
            if(view.findViewById<TextView>(R.id.mensajeChat) != null){
                mensaje = view.findViewById<TextView>(R.id.mensajeChat)
                hora = view.findViewById<TextView>(R.id.horaMensaje)
            }else{
                mensaje = view.findViewById<TextView>(R.id.mensajeResivido)
                hora = view.findViewById<TextView>(R.id.horaRecivido)
                esRecivido = 1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderMensajes {
        var itemView:View
        if(esRecivido == 0){
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.activity_rv_vista_mensaje,
                parent,
                false)
        }else{
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.activity_rv_vista_mensaje_contacto,
                parent,
                false)
        }

        return MyViewHolderMensajes(itemView)
    }

    override fun getItemCount(): Int {
        return this.mensajes.size
    }

    override fun onBindViewHolder(holder: MyViewHolderMensajes, position: Int) {
        val mensajeActual = this.mensajes[position]
        holder.mensaje.text = mensajeActual.contenido
        holder.hora.text = mensajeActual.hora
    }
}