package com.example.ready4evau.database

import android.provider.BaseColumns

class DBPregunta {
    class PreguntaEntry : BaseColumns{
        companion object{
            val TABLE_NAME = "preguntas"
            val COLUMN_PREGUNTA_ID = "id_Pregunta"
            val COLUMN_ENUNCIADO = "enunciado"
            val COLUMN_SOLUCION = "solucion"
            val COLUMN_ASIGNATURA = "asignatura"
            val COLUMN_TEMA = "tema"
            val COLUMN_NUMERO_ENUNCIADOS = "numeroEnunciados"

            val CREAR_TABLA_PREGUNTA ="CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_PREGUNTA_ID+ " INTEGER NOT NULL UNIQUE," +
                    COLUMN_ENUNCIADO + " TEXT," +
                    COLUMN_SOLUCION + " TEXT," +
                    COLUMN_ASIGNATURA + " TEXT," +
                    COLUMN_TEMA + " TEXT," +
                    COLUMN_NUMERO_ENUNCIADOS + " INTEGER," +
                    "PRIMARY KEY( "+ COLUMN_PREGUNTA_ID+" AUTOINCREMENT)" +
                    ")"


        }


    }
}