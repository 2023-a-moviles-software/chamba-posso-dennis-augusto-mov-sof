package com.example.examen

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EsqliteHelper (
    contexto: Context?,
): SQLiteOpenHelper(
    contexto,
    "Deber3",
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase) {
      val scriptDocente = """ 
           CREATE TABLE DOCENTE (
                CI_DOCENTE       CHAR(10)     PRIMARY KEY NOT NULL,
                NOMBRE           CHAR(50),
                NUMERO_OFICINA   INTEGER,
                FACULTAD         CHAR(50),
                HORARIOATENCION  TEXT
            );
            """.trimIndent()
      val scriptTarea = """
            CREATE TABLE TAREA (
                ID_TAREA         INTEGER      PRIMARY KEY AUTOINCREMENT,
                CI_DOCENTE       CHAR(10)     NOT NULL,
                FECHA_ENTREGA    CHAR(15),
                MATERIA          CHAR(20),
                ENTREGADO        INTEGER,
                CALIFICACION     REAL,
                DESCRIPCION      TEXT,
                FOREIGN KEY (CI_DOCENTE) REFERENCES DOCENTE (CI_DOCENTE)
            );
      """.trimIndent()
        db.execSQL(scriptDocente)
        db.execSQL(scriptTarea)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}