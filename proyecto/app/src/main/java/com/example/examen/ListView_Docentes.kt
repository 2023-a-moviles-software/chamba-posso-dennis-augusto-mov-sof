package com.example.examen

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ListView_Docentes : AppCompatActivity() {

    //base de datos
    private lateinit var docentes: List<Docente>
    var idItemSeleccionado = 0
    var cedulaDocente = ""

    companion object{
        lateinit var adaptadorDocente: ArrayAdapter<Docente>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_docentes)

        cargarDocentes()

        //crear docente
        val botonCrear = findViewById<Button>(R.id.btn_crear_docente)
        botonCrear.setOnClickListener{
            irActividad(crear_docente::class.java)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_context_docente, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_editar_maestro -> {
                irActividad(editar_docente::class.java)
                return true
            }

            R.id.menu_eliminar_docente -> {
                val docente = docentes[idItemSeleccionado]
                cedulaDocente = docente.Cedula

                val eliminacion = BaseDatos.eliminarDocente(cedulaDocente)

                cargarDocentes()

                adaptadorDocente.notifyDataSetChanged()
                return true
            }

            R.id.menu_tarea_maestro -> {
                val docente = docentes[idItemSeleccionado]
                cedulaDocente = docente.Cedula

                irActividad(ListView_Tareas::class.java)
                return true
            }

            else -> super.onContextItemSelected(item)

        }
    }

    fun irActividad(
        clase: Class<*>
    ) {

        val intent = Intent(this, clase)
        intent.putExtra("idItemSeleccionado", idItemSeleccionado)
        intent.putExtra("cedulaDocente", cedulaDocente)

        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

    }
    private fun cargarDocentes(){
        GlobalScope.launch(Dispatchers.Main){
            try{
                docentes = BaseDatos.mostrarDocentes()

                adaptadorDocente = ArrayAdapter(
                    this@ListView_Docentes,
                    android.R.layout.simple_list_item_1,
                    docentes
                )

                //adaptador
                val lista = findViewById<ListView>(R.id.lv_docentes)

                lista.adapter = adaptadorDocente
                adaptadorDocente.notifyDataSetChanged()
                registerForContextMenu(lista)
            }catch (e: Exception){}
        }

    }


}
