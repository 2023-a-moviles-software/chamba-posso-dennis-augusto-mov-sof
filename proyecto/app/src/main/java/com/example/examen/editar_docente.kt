package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class editar_docente : AppCompatActivity() {

    var itemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_docente)

        itemSeleccionado = intent.getIntExtra("idItemSeleccionado", 0)

        rellenarInformacion()

        val guardar = findViewById<Button>(R.id.btn_modificar)
        guardar.setOnClickListener{
            actualizarDocente()
        }
    }

    fun rellenarInformacion(){
        val infoDocente = BaseDatos.arregloDocentes[itemSeleccionado]

        val cuadroNombre = findViewById<TextView>(R.id.txt_nombre_docente_mod)
        val cuadroCedula = findViewById<TextView>(R.id.txt_cedula_docente_mod)
        val cuadroOficina = findViewById<TextView>(R.id.txt_oficina_docente_mod)
        val cuadroTutorias = findViewById<TextView>(R.id.txt_horario_tutorias_mod)
        val cuadroFacultad = findViewById<TextView>(R.id.txt_facultad_docente_mod)

        cuadroNombre.text = infoDocente.Nombre
        cuadroCedula.text = infoDocente.Cedula
        cuadroOficina.text = infoDocente.Numero_Oficina.toString()
        cuadroTutorias.text = infoDocente.mostrarHorarioAtencion()
        cuadroFacultad.text = infoDocente.Facultad

        cuadroCedula.keyListener = null
        cuadroCedula.isEnabled = false
    }

    fun actualizarDocente(){
        val cuadroNombre = findViewById<TextView>(R.id.txt_nombre_docente_mod)
        val cuadroCedula = findViewById<TextView>(R.id.txt_cedula_docente_mod)
        val cuadroOficina = findViewById<TextView>(R.id.txt_oficina_docente_mod)
        val cuadroTutorias = findViewById<TextView>(R.id.txt_horario_tutorias_mod)
        val cuadroFacultad = findViewById<TextView>(R.id.txt_facultad_docente_mod)

        val verificador = BaseDatos.modificarDocente(
            Docente(
            cuadroNombre.text.toString(),
            cuadroCedula.text.toString(),
            cuadroOficina.text.toString().toInt(),
            cuadroTutorias.text.toString().split(','),
            cuadroFacultad.text.toString()
            )
        )
        if (verificador){

            val intent = Intent(this, ListView_Docentes::class.java)
            startActivity(intent)
        }else{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Error al momento de modificar")

            alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

    }
}