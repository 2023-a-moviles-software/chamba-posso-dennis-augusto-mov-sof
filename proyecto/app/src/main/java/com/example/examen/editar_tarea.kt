package com.example.examen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        val tareaSeleccionada = ListView_Tareas.tareasTotales[idTareaSeleccionada]

        val descripcion = findViewById<TextView>(R.id.txt_descripcion_mod)
        val fecha = findViewById<TextView>(R.id.txt_fecha_mod)
        val Materia = findViewById<TextView>(R.id.txt_materia_mod)
        val entrega = findViewById<Switch>(R.id.sw_entrega_mod)
        val calificacion = findViewById<TextView>(R.id.txt_nota_mod)

        descripcion.text = tareaSeleccionada.Descripcion
        fecha.text = tareaSeleccionada.Fecha_Entrega
        Materia.text = tareaSeleccionada.Materia
        entrega.isChecked = tareaSeleccionada.Entregado
        calificacion.text = tareaSeleccionada.calificacion.toString()
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    fun actualizarTarea(){
        val tareaSeleccionada = ListView_Tareas.tareasTotales[idTareaSeleccionada]

        val descripcion = findViewById<TextView>(R.id.txt_descripcion_mod)
        val fecha = findViewById<TextView>(R.id.txt_fecha_mod)
        val Materia = findViewById<TextView>(R.id.txt_materia_mod)
        val entrega = findViewById<Switch>(R.id.sw_entrega_mod)
        val calificacion = findViewById<TextView>(R.id.txt_nota_mod)

        BaseDatos.modificarTarea(
            Tarea(
            tareaSeleccionada.id,
            descripcion.text.toString(),
            fecha.text.toString(),
            Materia.text.toString(),
            tareaSeleccionada.CedulaDocente,
            entrega.isChecked,
            calificacion.text.toString().toDouble()
        ))

        var nuevaTarea = Tarea(tareaSeleccionada.id,
            descripcion.text.toString(),
            fecha.text.toString(),
            Materia.text.toString(),
            tareaSeleccionada.CedulaDocente,
            entrega.isChecked,
            calificacion.text.toString().toDouble())

        ListView_Tareas.tareasTotales[idTareaSeleccionada] = nuevaTarea

        ListView_Tareas.adaptadorTarea.notifyDataSetChanged()
        val intent = Intent(this, ListView_Tareas::class.java)
        intent.putExtra("idItemSeleccionado", idTareaSeleccionada)
        intent.putExtra("cedulaDocente", cedulaDocente)
        startActivity(intent)
    }
}