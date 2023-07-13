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

    fun getNombre(): String{
        return Nombre
    }
    fun getCedula(): String{
        return Cedula
    }
    fun getNumeroCedula():Int{
        return Numero_Oficina
    }
    fun getHorario_Atencion(): String{
        var lista: String = ""
        for (x in Horario_Atencion){
            lista = lista + ", "+ x
        }
        return lista
    }
    fun getFacultad(): String{
        return Facultad
    }

    override fun toString(): String{
        return "${Cedula} - ${Nombre} - ${Facultad}"
    }
}