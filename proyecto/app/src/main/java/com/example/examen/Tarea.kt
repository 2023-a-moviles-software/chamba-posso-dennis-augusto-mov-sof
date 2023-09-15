package com.example.examen


class Tarea {
    var id: String
    var Descripcion: String
    var Fecha_Entrega: String
    var Materia: String
    var CedulaDocente: String
    var Entregado: Boolean
    var calificacion: Double

    constructor(
        id: String,
        Descripcion: String,
        Fecha_Entrega: String,
        Materia: String,
        CedulaDocente: String,
        Entregado: Boolean,
        calificacion: Double
    ){
        this.id = id
        this.Descripcion = Descripcion
        this.Fecha_Entrega = Fecha_Entrega
        this.Materia = Materia
        this.CedulaDocente = CedulaDocente
        this.Entregado = Entregado
        this.calificacion = calificacion
    }

    override fun toString(): String {
        return "${id}) ${Descripcion} \n"
    }
}