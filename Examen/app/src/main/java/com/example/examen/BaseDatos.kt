package com.example.examen

import java.util.Date


class BaseDatos {
    companion object{
        val arregloDocentes = mutableListOf<Docente>(
            Docente(
                "Juan Perez",
                "1725661183",
                15,
                arrayOf("lunes -> 13:00"),
                "Matematicas"),
            Docente(
                "Lucia Hernandez",
                "1714176813",
                62,
                arrayOf("martes -> 14:00", "jueves -> 11:00"),
                "Sistemas"),
        )

        val arregloTareas = mutableListOf<Tarea>(
            Tarea(
                1,
                "Mapa mental",
                Date("01-02-2023"),
                "Probabilidad y estadistica",
                arregloDocentes[0].Cedula,
                false,
                0.0)
        )
    }

    //buscar docentes
    fun buscarDocente(cedula: String): Docente? {
        return arregloDocentes.find{ it.Cedula == cedula }
    }
    //buscar tareas
    fun buscarTareas(id: Int): Tarea? {
        return arregloTareas.find{it.id == id}
    }

    //eliminar docente
    fun eliminarDocente(cedula: String): Boolean{
        val docenteBuscado = buscarDocente(cedula)
        if(docenteBuscado != null){
            val indice = arregloDocentes.indexOf(docenteBuscado)
            arregloDocentes.remove(docenteBuscado)
            return true
        }
        return false
    }

    //eliminar tarea
    fun eliminarTarea(id)
}