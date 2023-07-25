package com.example.moviles_2023a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador (
    contexto: Context?, //this
): SQLiteOpenHelper(
    contexto,
    "Moviles", //nombre bd
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase){
        val scriptSQLCrearTablaEntrenador = """
            CREATE TABLE ENTRENADOR(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre VARCHAR(50),
            descripcion VARCHAR(50))
        """.trimIndent()
        db.execSQL(scriptSQLCrearTablaEntrenador) //ejecuta el script
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion:Int){
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean{
        val  db = this.writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = db.insert(
            "ENTRENADOR",  //nombre tabla
            null,
            valoresAGuardar,  // valores
        )
        db.close()
        return resultadoGuardar.toInt() != -1
    }


    fun actualizarEntrenadorFormulario(nombre:String, descripcion:String, id:Int):Boolean{
        val db = this.writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        //where id = ?
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = db
            .update(
                "ENTRENADOR",  // nombre tabla
                valoresAActualizar,
                "id =?", //consulta where
                parametrosConsultaActualizar
            )
        db.close()
        return resultadoActualizacion.toInt() != -1
    }
    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val db = this.writableDatabase
        //where id = ?
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = db
            .delete(
                "ENTRENADOR", // nombre tabla
                "id =?", //consulta where
                parametrosConsultaDelete
            )
        db.close()
        return resultadoEliminacion.toInt() != -1
    }



    fun consultarEntrenadorPorID(id: Int): BEntrenador{
        val db = this.readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = db.rawQuery(
            scriptConsultaLectura, //Consulta
            parametrosConsultaLectura, // parametros
        )
        //logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "", "")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)  //indice 0
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if(id != null){
                    //llenar el arreglo con un nuevo entrenador
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        db.close()
        return usuarioEncontrado
    }

}