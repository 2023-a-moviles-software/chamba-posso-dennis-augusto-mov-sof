package com.example.moviles_2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import java.util.Date

class IFirestore : AppCompatActivity() {

    var query: Query? = null
    var arreglo: ArrayList<ICities> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirestore)

        //configuracion del listview

        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arreglo)
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener{crearDatosPrueba()}

        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener{consultarConOrderBy(adaptador)}

        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonOrderBy.setOnClickListener{consultarDocumento(adaptador)}

        val consultarIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        consultarIndiceCompuesto.setOnClickListener{consutarIndiceCompuesto(adaptador)}

        val botonCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonCrear.setOnClickListener{crearEjemplo()}

        val botonEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonEliminar.setOnClickListener{eliminar()}

        //empezar a paginar
        val botonEmpezarPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonEmpezarPaginar.setOnClickListener{query = null; consultarCuidades(adaptador)}
        val botonPaginar = findViewById<Button>(R.id.btn_fs_paginar)
        botonEmpezarPaginar.setOnClickListener{consultarCuidades(adaptador)}
    }

    fun consultarCuidades(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRef = db.collection("cities")
            .orderBy("population")
            .limit(1)

        var tarea: Task<QuerySnapshot>? = null
        if (query == null){
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            tarea = citiesRef.get()
        }else{
            tarea = query!!.get()
        }

        if(tarea != null){
            tarea.addOnSuccessListener {
                documentSnapshots ->
                guardarQuery(documentSnapshots, citiesRef)
                for (ciudad in documentSnapshots){
                    anadirArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
                .addOnFailureListener {  }
        }
    }

    fun guardarQuery(
        documentSnapshot: QuerySnapshot,
        refCities: Query
    ){
        if(documentSnapshot.size() > 0){
            val ultimoDocumento = documentSnapshot
                .documents[documentSnapshot.size()-1]
            query = refCities
                .startAfter(ultimoDocumento)
            /*
            db.collection("cities")
            .orderBy("population")
            .limit(1)
            .startAfter(ultimoDocumento)
            .get()
            */
        }
    }

    fun eliminar(){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("ejemplo")
        referenciaEjemploEstudiante
            .document("1725661183")
            .delete()
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    fun crearEjemplo(){
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
        // .document("id_hijo")
        // .document("estudiante")
        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "dennis",
            "graduado" to false,
            "promedio" to 14.00,
            "direccion" to hashMapOf(
                "direccion" to "El condadp",
                "numeroCalle" to 1887
            ),
            "materias" to listOf("web", "moviles")
        )
        //identificador quemado
        referenciaEjemploEstudiante
            .document("1725661183")
            .set(datosEstudiante)
            .addOnFailureListener{}
            .addOnFailureListener{}
        //identidicado quemado pero autogenerado con date().time
        referenciaEjemploEstudiante
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener {  }
            .addOnFailureListener {}
        //sin identificador
        referenciaEjemploEstudiante
            .add(datosEstudiante)
            .addOnCompleteListener{}
            .addOnFailureListener{}
    }

    fun consutarIndiceCompuesto(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        citiesRefUnico
            .whereEqualTo("capital", false)
            .whereLessThan("population", 4000000)
            .orderBy("population", Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for(ciudad in it){
                    anadirArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{}
    }
    fun consultarDocumento(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        /*
        COleccion ciudad
           -> ciudad barrio
              -> coleccion direccion
        quito -> la_floresta -> E90-001
        db.collection('ciudad').document("quito")
        .collection("barrio").document("la_floresta")
        .collection("direccion").document("E90-001")

        .colection("nombre coleccion hijo").document("id hijo")
        */
        citiesRefUnico
            .document("Bj")
            .get()
            .addOnSuccessListener {
                arreglo
                    .add(
                        ICities(
                            it.data?.get("name") as String?,
                            it.data?.get("state") as String?,
                            it.data?.get("country") as String?,
                            it.data?.get("capital") as Boolean?,
                            it.data?.get("population") as Long?,
                            it.data?.get("regions") as ArrayList<String>,
                        )
                    )
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{
                //erroers
            }
    }

    fun limpiarArreglo(){
        arreglo.clear()
    }
    fun consultarConOrderBy(adaptador: ArrayAdapter<ICities>){
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico
            .orderBy("population", Direction.ASCENDING)
            .get()
            .addOnSuccessListener {//it -> eso (lo que llegue)
                for(ciudad in it){
                    ciudad.id
                    anadirArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{
                // errores
            }
    }

    fun anadirArregloCiudad(
        ciudad: QueryDocumentSnapshot
    ){
        val nuevaCiudad = ICities(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?,
            ciudad.data.get("regions") as ArrayList<String>,
        )
        arreglo.add(nuevaCiudad)
    }
    fun crearDatosPrueba(){
        val db = Firebase.firestore

        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)
    }
}