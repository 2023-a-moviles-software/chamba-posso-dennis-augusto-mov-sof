package com.example.moviles_2023a

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    val listaEntrenadores: ArrayList<BEntrenador> = BBaseDatoMemoria.arregloBEntrenador
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        //oobtener referencia a listview
        val listView = findViewById<ListView>(R.id.lv_list_view)

        //adaptador del arreglo de entrenadores
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //layod.xml que se va usar
            listaEntrenadores
        )
        // seteo el adaptador a list view
        listView.adapter = adaptador
        // se refresca el adaptador con cambios
        adaptador.notifyDataSetChanged()

        // boton para agregar Entrenador
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView.setOnClickListener{
            agregarEntrenador(adaptador)
        }
        // registrar el listView para el menu contextual
        registerForContextMenu(listView)
    }

    fun agregarEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        listaEntrenadores.add(
            BEntrenador(listaEntrenadores.size, "Adriam", "Descripcion")
        )
        adaptador.notifyDataSetChanged() //esto es para que se notifiquen los cambios y se muestren en el layou

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        //llenar opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //obtener el di del arrraylist seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar ->{
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "Eliminar a : ${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ //callback
                dialog, which ->  //alguna cosa
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val opciones = resources.getStringArray(R.array.string_array_dialog)
        val seleccionPrevia = booleanArrayOf(
            true, //lunes seleccionado
            false, //martes no seleccionado
            false //miercoles no seleccionado
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
         {
                 dialog, which, isChecked -> "Dio click en le item ${which}"
         }
        )
        val dialogo = builder.create()
        dialogo.show()
    }




}