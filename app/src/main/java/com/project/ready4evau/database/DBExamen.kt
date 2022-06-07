package com.project.ready4evau.database

import android.provider.BaseColumns

class DBExamen {
    class ExamenEntry : BaseColumns {
        companion object{
            val TABLE_NAME = "examen"
            val COLUMN_EXAMEN_ID = "id_Examen"
            val COLUMN_PREGUNTA1 = "pregunta1"
            val COLUMN_PREGUNTA2 = "pregunta2"
            val COLUMN_PREGUNTA3 = "pregunta3"
            val COLUMN_PREGUNTA4 = "pregunta4"
            val COLUMN_PREGUNTA5 = "pregunta5"
            val COLUMN_ASIGNATURA = "asignatura"
            val COLUMN_TEMA = "tema"
            val COLUMN_NOTA = "nota"
            val COLUMN_FECHA = "fecha"
            val COLUMN_USER = "user"

            val CREAR_TABLA_EXAMEN ="CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_EXAMEN_ID+ " INTEGER NOT NULL UNIQUE," +
                    COLUMN_PREGUNTA1 + " INTEGER," +
                    COLUMN_PREGUNTA2 + " INTEGER," +
                    COLUMN_PREGUNTA3 + " INTEGER," +
                    COLUMN_PREGUNTA4 + " INTEGER," +
                    COLUMN_PREGUNTA5 + " INTEGER," +
                    COLUMN_ASIGNATURA + " TEXT," +
                    COLUMN_TEMA + " TEXT," +
                    COLUMN_NOTA + " TEXT," +
                    COLUMN_FECHA + " TEXT," +
                    COLUMN_USER + " TEXT," +
                    "PRIMARY KEY( "+ COLUMN_EXAMEN_ID+" AUTOINCREMENT)" +
                    ")"


        }


    }
}