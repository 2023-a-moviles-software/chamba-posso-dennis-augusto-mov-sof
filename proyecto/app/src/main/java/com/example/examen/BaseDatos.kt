package com.example.examen

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class BaseDatos {
    companion object{

        private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        private val docentesCollection = db.collection("docentes")
        private val tareasCollection = db.collection("tarea")

        var arregloDocentes = mutableListOf<Docente>()
        var arregloTareas = mutableListOf<Tarea>()

        suspend fun mostrarDocentes(): MutableList<Docente> {
            arregloDocentes = mutableListOf<Docente>()

            try{
                val querySnapshot = docentesCollection.get().await()

                if(!querySnapshot.isEmpty){
                    for (document in querySnapshot){
                        val horarioAtencionList = document.get("horario_Atencion") as? List<String>
                        arregloDocentes.add(
                            Docente(
                                document.getString("nombre")?:"",
                                document.getString("cedula")?: "",
                                document.getLong("numero_Oficina")?.toInt()?: 0,
                                horarioAtencionList?: emptyList(),
                                document.getString("facultad")?:""

                            )
                        )
                    }
                }


            }catch (e: Exception){}
            return arregloDocentes
        }

        suspend fun mostrarTareas(): MutableList<Tarea> {
            val arregloTareas = mutableListOf<Tarea>()

            try{
                val querySnapshot = tareasCollection.get().await()

                if(!querySnapshot.isEmpty){
                    for (document in querySnapshot){
                        arregloTareas.add(
                            Tarea(
                                document.getString("id")?:"",
                                document.getString("descripcion")?: "",
                                document.getString("fecha_Entrega")?: "",
                                document.getString("materia")?: "",
                                document.getString("cedulaDocente")?: "",
                                document.getBoolean("entregado")?: false,
                                document.getLong("calificacion")?.toDouble()?:0.0
                            )
                        )
                    }
                }


            }catch (e: Exception){
                Log.e("mostrarTareas", "Error al recuperar las tareas", e)
            }
            return arregloTareas
        }


        //eliminar docente
        fun eliminarDocente(cedula: String): Boolean {
            docentesCollection
                .document(cedula)
                .delete()
                .addOnSuccessListener {}
                .addOnFailureListener { e ->                }
            return true
        }

        //eliminar tarea
        fun eliminarTarea(id: String): Boolean{
            tareasCollection
                .document(id)
                .delete()
                .addOnSuccessListener {}
                .addOnFailureListener { e ->                }
            return true
        }

        //modificar docente

        fun modificarDocente(docenteModificado: Docente): Boolean {
            val cedula = docenteModificado.Cedula

                // Actualiza el docente en Firestore
            docentesCollection
                    .document(cedula)
                    .set(docenteModificado)
                    .addOnSuccessListener { }
                    .addOnFailureListener { e -> }


            return true
        }


        //modificar tarea
        fun modificarTarea(tareaModificada: Tarea): Boolean{
            val idTarea = tareaModificada.id

            tareasCollection
                .document(idTarea)
                .set(tareaModificada)
                .addOnSuccessListener {  }
                .addOnFailureListener {  }
            return true
        }

        //agreagar docente
        fun agregarDocente(profe: Docente): Boolean{
            docentesCollection
                .document(profe.Cedula)
                .set(profe)
                .addOnSuccessListener { }
                .addOnFailureListener {  }
            return true
        }

        //agregar tarea
        fun agregarTarea(deber: Tarea): Boolean{
            tareasCollection
                .document(deber.id)
                .set(deber)
                .addOnSuccessListener {  }
                .addOnFailureListener {  }
            return true
        }



    }




}