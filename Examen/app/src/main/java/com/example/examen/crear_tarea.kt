package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import java.text.SimpleDateFormat

class crear_tarea : AppCompatActivity() {

    var docenteCedula = ""
    var idTareaSeleccionada = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tarea)

        idTareaSeleccionada = intent.getIntExtra("idItemSeleccionado",0)
        docenteCedula = intent.getStringExtra("cedulaDocente")?: docenteCedula

        val botonAgregar = findViewById<Button>(R.id.btn_guardar_tarea)
        botonAgregar.setOnClickListener{

            val formato = SimpleDateFormat("dd/MM/yyyy")

            val descripcion = findViewById<TextView>(R.id.txt_descripcion)
            val fecha = findViewById<TextView>(R.id.txt_fecha)
            val Materia = findViewById<TextView>(R.id.txt_materia)
            val entrega = findViewById<Switch>(R.id.sw_entrega)
            val calificacion = findViewById<TextView>(R.id.txt_nota)

            BaseDatos.agregarTarea(Tarea(
                BaseDatos.arregloTareas.size+1,
                descripcion.toString(),
                formato.parse(fecha.toString()),
                Materia.toString(),
                docenteCedula,
                entrega.isChecked(),
                calificacion.toString().toDouble()
            ))

            ListView_Tareas.adaptadorTarea.notifyDataSetChanged()
            finish()
        }
    }


}