package com.example.moviles_2023a

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ECrudEntrenador : AppCompatActivity() {

    private var idField:EditText? = null
    private var nombreField:EditText? = null
    private var descripcionField:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        idField = findViewById<EditText>(R.id.input_id)
        nombreField = findViewById<EditText>(R.id.input_nombre)
        descripcionField = findViewById<EditText>(R.id.input_descripcion)

        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {

                val idInt = idField!!.text.toString().toInt()

                if(EBaseDatos.tablaEntrenador != null && idInt != null){
                    val entrenador = EBaseDatos.tablaEntrenador?.consultarEntrenadorPorID(idInt)
                    if(entrenador != null){
                        idField!!.setText(idInt.toString())
                        nombreField!!.setText(entrenador.nombre)
                        descripcionField!!.setText(entrenador.descripcion)
                    }
                }
            }

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener {
                val nombre = nombreField!!.text.toString()
                val descripcion = descripcionField!!.text.toString()
                EBaseDatos.tablaEntrenador!!.crearEntrenador(
                    nombre, descripcion
                )
            }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
                val id =  idField!!.text.toString().toInt()
                val nombre = nombreField!!.text.toString()
                val descripcion = descripcionField!!.text.toString()
                EBaseDatos.tablaEntrenador!!.actualizarEntrenadorFormulario(nombre, descripcion, id)
            }

        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
                val id =  idField!!.text.toString().toInt()
                EBaseDatos.tablaEntrenador!!.eliminarEntrenadorFormulario(id)
            }

    }


}