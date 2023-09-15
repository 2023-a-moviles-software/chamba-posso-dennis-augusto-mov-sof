package com.example.examen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ListView_Tareas : AppCompatActivity() {

    var idTareaSeleccionada = 0
    var cedulaDocente = ""
    var idDocenteSeleccionado = 0


    companion object{
        lateinit var adaptadorTarea: ArrayAdapter<Tarea>
        var tareasTotales = mutableListOf<Tarea>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_tareas)
        val listViewTarea = findViewById<ListView>(R.id.lv_tareas)

        //datos del docente
        idDocenteSeleccionado = intent.getIntExtra("idItemSeleccionado", idDocenteSeleccionado)
        cedulaDocente = intent.getStringExtra("cedulaDocente")?: cedulaDocente

        //recuperacion de tareas del docente
        cargarTareas()



        //seteo del layoud
        val nombreDocente = findViewById<TextView>(R.id.nombre_docente)
        nombreDocente.text = BaseDatos.arregloDocentes[idDocenteSeleccionado].Nombre

        //agregar tarea
        val botonAgregarTarea = findViewById<Button>(R.id.btn_aniadir_tareas)
        val botonRegresar = findViewById<Button>(R.id.btn_regresar)

        botonAgregarTarea.setOnClickListener{
            irActividad(crear_tarea::class.java)
        }
        botonRegresar.setOnClickListener{
            irActividad(ListView_Docentes::class.java)
        }

        listViewTarea.setOnItemClickListener{
            parent, view, position, id ->
            val tareaSeleccionada = adaptadorTarea.getItem(position)
            irActividad(datosTarea::class.java)
        }

        registerForContextMenu(listViewTarea)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_context_tarea, menu)


        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idTareaSeleccionada = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_editar_tarea -> {
                irActividad(editar_tarea::class.java)
                return true

            }

            R.id.menu_eliminar_tarea -> {
                val idtarea = tareasTotales[idTareaSeleccionada].id
                BaseDatos.eliminarTarea(idtarea)

                cargarTareas()

                adaptadorTarea.notifyDataSetChanged()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idItemSeleccionado", idTareaSeleccionada)
        intent.putExtra("cedulaDocente", cedulaDocente)
        startActivity(intent)
    }


    private fun cargarTareas(){
        GlobalScope.launch(Dispatchers.Main){
            try{
                val listViewTarea = findViewById<ListView>(R.id.lv_tareas)

                tareasTotales = BaseDatos.mostrarTareas()

                adaptadorTarea = ArrayAdapter(
                    this@ListView_Tareas,
                    android.R.layout.simple_list_item_1,
                    tareasTotales.filter{tarea -> tarea.CedulaDocente == cedulaDocente } as ArrayList<Tarea>
                )

                //adaptador

                listViewTarea.adapter = adaptadorTarea
                adaptadorTarea.notifyDataSetChanged()
                registerForContextMenu(listViewTarea)

            }catch (e: Exception){}
        }

    }



}