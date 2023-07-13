package com.example.examen

import java.util.Date

class Tarea {
    val id: Int
    val Descripcion: String
    val Fecha_Entrega: Date
    val Materia: String
    val CedulaDocente: String
    val Entregado: Boolean
    val calificacion: Double

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


}