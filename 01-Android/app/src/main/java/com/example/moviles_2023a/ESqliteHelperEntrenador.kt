package com.example.moviles_2023a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador (
    contexto: Context?, //this
): SQLiteOpenHelper(
    contexto,
    "moviles", //nombre bd
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?){
        val scriptSQLCrearTablaEntrenador = """
            CREATE TABLE ENTRENADOR(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre VARCHAR(50),
            desscripcion VARCHAR(50))
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador) //ejecuta el script
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion:Int){
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean{
        val  baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscritura.insert(
            "ENTRENADOR",  //nombre tabla
            null,
            valoresAGuardar,  // valores
        )
        baseDatosEscritura.close()
        return if(resultadoGuardar.toInt() === -1) false else true
    }

    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        //where id = ?
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "Entrenador", // nombre tabla
                "id =?", //consulta where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() === -1) false else true
    }

    fun actualizarEntrenadorFormulario(nombre:String, descripcion:String, id:Int):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        //where id = ?
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "Entrenador",  // nombre tabla
                valoresAActualizar,
                "id =?", //consulta where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt() === -1) false else true
    }

    fun consultarEntrenadorPorID(id: Int): BEntrenador{
        val baseDatosLectura = readableDatabase
        val scrptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scrptConsultaLectura, //Consulta
            parametrosConsultaLectura, // parametros
        )
        //logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "", "")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)  //indice 0
                val nombre = resultadoConsultaLectura.getInt(1)
                val descripcion = resultadoConsultaLectura.getInt(2)
                if(id != null){
                    //llenar el arreglo con un nuevo entrenador
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre.toString()
                    usuarioEncontrado.descripcion = descripcion.toString()
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

}