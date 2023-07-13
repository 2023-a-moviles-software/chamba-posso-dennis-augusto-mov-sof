package com.example.examen

class Docente {

    var Nombre: String
    var Cedula: String
    var Numero_Oficina: Int
    var Horario_Atencion: Array<String>
    var Facultad: String

    constructor(
        Nombre: String,
        Cedula: String,
        Numero_Oficina: Int,
        Horario_Atencion: Array<String>,
        Facultad: String
    ){
        this.Nombre = Nombre
        this.Cedula = Cedula
        this.Numero_Oficina = Numero_Oficina
        this.Horario_Atencion = Horario_Atencion
        this.Facultad = Facultad
    }


    fun mostrarHorarioAtencion(): String{
        var lista: String = ""
        for (x in Horario_Atencion){
            lista = lista + ", "+ x
        }
        return lista
    }



    override fun toString(): String{
        return "Nombre:${Nombre} \n" +
                "Cedula: ${Cedula} \n" +
                "Facultad: ${Facultad} \n " +
                "Oficina: ${Numero_Oficina} \n " +
                "Tutorias: ${mostrarHorarioAtencion()}"
    }
}