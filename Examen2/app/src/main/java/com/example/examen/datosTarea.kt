package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import java.text.SimpleDateFormat

class datosTarea : AppCompatActivity() {

    var idTareaSeleccionada = 0
    var cedulaDocente = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_tarea)
        idTareaSeleccionada = intent.getIntExtra("idItemSeleccionado",0)
        cedulaDocente = intent.getStringExtra("cedulaDocente")?: cedulaDocente

        mostrarInfo()
    }

    fun mostrarInfo(){
        val tareaSeleccionada = ListView_Tareas.tareasDelDocente[idTareaSeleccionada]

        val formato = SimpleDateFormat("dd/MM/yyyy")

        val descripcion = findViewById<TextView>(R.id.descripcion_datos)
        val fecha = findViewById<TextView>(R.id.fecha_datos)
        val Materia = findViewById<TextView>(R.id.materia_datos)
        val entrega = findViewById<Switch>(R.id.entrega_datos)
        val calificacion = findViewById<TextView>(R.id.notaDatos)

        descripcion.text = tareaSeleccionada.Descripcion
        fecha.text = formato.format(tareaSeleccionada.Fecha_Entrega)
        Materia.text = tareaSeleccionada.Materia
        entrega.isChecked = tareaSeleccionada.Entregado
        calificacion.text = tareaSeleccionada.calificacion.toString()

        descripcion.isEnabled = false
        fecha.isEnabled = false
        Materia.isEnabled = false
        entrega.isEnabled = false
        calificacion.isEnabled = false
    }
}