package com.example.examen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date

class editar_tarea : AppCompatActivity() {

    var idTareaSeleccionada = 0
    var cedulaDocente = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tarea)

        idTareaSeleccionada = intent.getIntExtra("idItemSeleccionado",0)
        cedulaDocente = intent.getStringExtra("cedulaDocente")?: cedulaDocente

        mostrarInfo()

        val botonGuardarCambios = findViewById<Button>(R.id.btn_guardar_tarea_cambios)
        botonGuardarCambios.setOnClickListener{
            actualizarTarea()
        }

    }

    fun mostrarInfo(){
        val tareaSeleccionada = ListView_Tareas.tareasDelDocente[idTareaSeleccionada]

        val formato = SimpleDateFormat("dd/MM/yyyy")

        val descripcion = findViewById<TextView>(R.id.txt_descripcion_mod)
        val fecha = findViewById<TextView>(R.id.txt_fecha_mod)
        val Materia = findViewById<TextView>(R.id.txt_materia_mod)
        val entrega = findViewById<Switch>(R.id.sw_entrega_mod)
        val calificacion = findViewById<TextView>(R.id.txt_nota_mod)

        descripcion.text = tareaSeleccionada.Descripcion
        fecha.text = formato.format(tareaSeleccionada.Fecha_Entrega)
        Materia.text = tareaSeleccionada.Materia
        entrega.isChecked = tareaSeleccionada.Entregado
        calificacion.text = tareaSeleccionada.calificacion.toString()
    }

    fun actualizarTarea(){
        val tareaSeleccionada = ListView_Tareas.tareasDelDocente[idTareaSeleccionada]
        val formato = SimpleDateFormat("dd/MM/yyyy")

        val descripcion = findViewById<TextView>(R.id.txt_descripcion_mod)
        val fecha = findViewById<TextView>(R.id.txt_fecha_mod)
        val Materia = findViewById<TextView>(R.id.txt_materia_mod)
        val entrega = findViewById<Switch>(R.id.sw_entrega_mod)
        val calificacion = findViewById<TextView>(R.id.txt_nota_mod)

        BaseDatos.modificarTarea(
            tareaSeleccionada.id,
            descripcion.toString(),
            formato.parse(fecha.toString()),
            Materia.toString(),
            tareaSeleccionada.CedulaDocente,
            entrega.isChecked(),
            calificacion.toString().toDouble()
        )

        var nuevaTarea = Tarea(tareaSeleccionada.id,
            descripcion.toString(),
            formato.parse(fecha.toString()),
            Materia.toString(),
            tareaSeleccionada.CedulaDocente,
            entrega.isChecked(),
            calificacion.toString().toDouble())

        ListView_Tareas.tareasDelDocente[idTareaSeleccionada] = nuevaTarea

        ListView_Tareas.adaptadorTarea.notifyDataSetChanged()
        finish()
    }
}