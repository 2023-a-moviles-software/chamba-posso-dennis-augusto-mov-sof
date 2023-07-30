package com.example.clonewhatsapp

import java.io.Serializable

class Contacto (
    var nombre: String,
    var imagen: String,
    var mensajes: ArrayList<Mensajes>
): Serializable