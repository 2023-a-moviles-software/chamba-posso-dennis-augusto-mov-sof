package com.example.moviles_2023a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FReciclerViewAdaptorNombreCedula (
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<FReciclerViewAdaptorNombreCedula.MyViewHolder>(){
        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
            val nombreTextView: TextView
            val cedulaTextView: TextView
            val likesTextView: TextView
            val accionBoton: Button
            var numeroLikes = 0

            init {
                nombreTextView = view.findViewById(R.id.tv_nombre)
                cedulaTextView = view.findViewById(R.id.tv_cedula)
                likesTextView = view.findViewById(R.id.tv_likes)
                accionBoton = view.findViewById(R.id.btn_dar_like)
                accionBoton.setOnClickListener{anadirLike()}
            }
            fun anadirLike(){
                numeroLikes++
                likesTextView.text = numeroLikes.toString()
                likesTextView.text = numeroLikes.toString()
                contexto.aumenraTotalLikes()
            }
        }

    //setear el layoud que vamos a usar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    //setear los datos para la iteracion al iniciar el adaptador
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorAcutal = this.lista[position]
        holder.nombreTextView.text = entrenadorAcutal.nombre
        holder.cedulaTextView.text = entrenadorAcutal.descripcion
        holder.accionBoton.text = "Like ${entrenadorAcutal.id} - ${entrenadorAcutal.nombre}"
        holder.likesTextView.text = "0"
    }
}