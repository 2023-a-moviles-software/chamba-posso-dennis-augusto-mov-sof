package com.example.examen

import java.util.Date

class Tarea {
    var id: Int
    var Descripcion: String
    var Fecha_Entrega: Date
    var Materia: String
    var CedulaDocente: String
    var Entregado: Boolean
    var calificacion: Double

    constructor(
        id: Int,
        Descripcion: String,
        Fecha_Entrega: Date,
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