package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class crear_docente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_docente)

        val botonCrear = findViewById<Button>(R.id.btn_guardarDocente)
        botonCrear.setOnClickListener{
            registrarDocente(ListView_Docentes.adaptadorDocente)
        }
    }

    fun registrarDocente(adaptador: ArrayAdapter<Docente>){
        val nombre = findViewById<EditText>(R.id.txt_nombre_docente)
        val cedula = findViewById<EditText>(R.id.txt_cedula_docente)
        val oficina = findViewById<EditText>(R.id.txt_oficina_docente)
        val tutorias = findViewById<EditText>(R.id.txt_horario_tutorias)
        val facultad = findViewById<EditText>(R.id.txt_facultad_docente)

        var verificar = BaseDatos.agregarDocente(
            Docente(
                nombre.text.toString(),
                cedula.text.toString(),
                oficina.text.toString().toInt(),
                tutorias.text.toString().split(",").toTypedArray(),
                facultad.text.toString()
            )
        )

        if (!verificar){
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Cédula ya ingresada")
            alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }else{
            adaptador.notifyDataSetChanged()
            finish()
        }


    }

}