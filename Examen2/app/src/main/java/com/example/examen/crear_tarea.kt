package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Switch
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

            val descripcion = findViewById<TextView>(R.id.txt_descripcion)
            val fecha = findViewById<TextView>(R.id.txt_fecha)
            val Materia = findViewById<TextView>(R.id.txt_materia)
            val entrega = findViewById<Switch>(R.id.sw_entrega)
            val calificacion = findViewById<TextView>(R.id.txt_nota)

            BaseDatos.agregarTarea(Tarea(
                SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(Date()),
                descripcion.text.toString(),
                fecha.text.toString(),
                Materia.text.toString(),
                docenteCedula,
                entrega.isChecked,
                calificacion.text.toString().toDouble()
            ))

            ListView_Tareas.adaptadorTarea.notifyDataSetChanged()

            val intent = Intent(this, ListView_Tareas::class.java)
            intent.putExtra("idItemSeleccionado", idTareaSeleccionada)
            intent.putExtra("cedulaDocente", docenteCedula)
            startActivity(intent)

        }

    }


}