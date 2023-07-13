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
    fun eliminarTarea(id: Int): Boolean{
        val tareaBuscada = buscarTareas(id)
        if(tareaBuscada != null){
            val indice = arregloTareas.indexOf(tareaBuscada)
            arregloTareas.remove(tareaBuscada)
            return true
        }
        return false
    }

    //modificar docente
    fun modificarDocente(
        nombre: String,
        cedula: String,
        oficina: Int,
        Horario: Array<String>,
        Facultad: String): Boolean{
        val docenteBuscado = buscarDocente(cedula)
        if(docenteBuscado != null){
            val indice = arregloDocentes.indexOf(docenteBuscado)
            arregloDocentes[indice].Cedula = cedula
            arregloDocentes[indice].Nombre = nombre
            arregloDocentes[indice].Numero_Oficina = oficina
            arregloDocentes[indice].Horario_Atencion = Horario
            arregloDocentes[indice].Facultad = Facultad
            return true
        }
        return false
    }

    //modificar tarea
    fun modificarTarea(
        id: Int,
        descripcion: String,
        fechaEntrega: Date,
        Materia: String,
        CedulaDocente: String,
        Entregado: Boolean,
        calificacion: Double): Boolean{
        val tareaBuscada = buscarTareas(id)
        if(tareaBuscada != null){
            val indice = arregloTareas.indexOf(tareaBuscada)
            arregloTareas[indice].id = id
            arregloTareas[indice].Descripcion = descripcion
            arregloTareas[indice].Fecha_Entrega = fechaEntrega
            arregloTareas[indice].Materia = Materia
            arregloTareas[indice].CedulaDocente = CedulaDocente
            arregloTareas[indice].Entregado = Entregado
            arregloTareas[indice].calificacion = calificacion
            return true
        }
        return false
    }

    //agreagar docente
    fun agregarDocente(profe: Docente): Boolean{
        if(buscarDocente(profe.getCedula()) == null){
            arregloDocentes.add(profe)
            return true
        }
        return false
    }

    //agregar tarea
    fun agregarTarea(deber: Tarea): Boolean{
        if(buscarTareas(deber.id) == null){
            arregloTareas.add(deber)
            return true
        }
        return false
    }


}