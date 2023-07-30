package com.example.clonewhatsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorMensajes (
    private val interfazChat: RVChat,
    private val mensajes: ArrayList<Mensajes>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<AdaptadorMensajes.MyViewHolderMensajes>(){

    // Define una constante para indicar si el mensaje es del usuario actual
    private val ES_MIO = 1
    private val ES_OTRO = 2

    inner class MyViewHolderMensajes(view: View): RecyclerView.ViewHolder(view){
        val mensaje: TextView
        val hora: TextView

        init{

            mensaje = view.findViewById<TextView>(R.id.mensajeChat)
            hora = view.findViewById<TextView>(R.id.horaMensaje)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderMensajes {
        var itemView = if(viewType == ES_MIO){
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_rv_vista_mensaje,
                parent,
                false)
        }else{
            LayoutInflater.from(parent.context).inflate(
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

    override fun getItemViewType(position: Int): Int {
        val mensajeActual = mensajes[position]
        return if (mensajeActual.esMio) {
            ES_MIO
        } else {
            ES_OTRO
        }
    }
}