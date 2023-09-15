package com.example.examen

class Docente {

    var Nombre: String
    var Cedula: String
    var Numero_Oficina: Int
    var Horario_Atencion: List<String>
    var Facultad: String

    constructor(
        Nombre: String,
        Cedula: String,
        Numero_Oficina: Int,
        Horario_Atencion: List<String>,
        Facultad: String
    ){
        this.Nombre = Nombre
        this.Cedula = Cedula
        this.Numero_Oficina = Numero_Oficina
        this.Horario_Atencion = Horario_Atencion
        this.Facultad = Facultad
    }


    fun mostrarHorarioAtencion(): String {
        val lista = StringBuilder()
        for (x in Horario_Atencion) {
            lista.append(", ").append(x)
        }

        // Verificar si la cadena tiene contenido antes de manipularla
        if (lista.isNotEmpty()) {
            // Eliminamos la coma inicial y los espacios en blanco adicionales
            val resultado = lista.substring(2).trim()
            return resultado
        } else {
            // En caso de que no haya elementos en la lista, retornamos una cadena vacía
            return ""
        }
    }



    override fun toString(): String{
        return "Nombre:${Nombre} \n" +
                "Cedula: ${Cedula} \n" +
                "Facultad: ${Facultad} \n " +
                "Oficina: ${Numero_Oficina} \n " +
                "Tutorias: ${mostrarHorarioAtencion()}"
    }
}