package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ListView_Docentes : AppCompatActivity() {

    //base de datos
    val docentes = BaseDatos.arregloDocentes

    companion object{
        lateinit var adapataroDocente: ArrayAdapter<Docente>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_docentes)

        //adaptador
        val lista = findViewById<ListView>(R.id.lv_docentes)
    }
}