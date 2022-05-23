package com.example.ready4evau.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class PreguntasDBHelper (context: Context): SQLiteOpenHelper(context, "Ready4Evau5.db", null, 2){
    override fun onCreate(db: SQLiteDatabase?) {


        db?.execSQL(DBPregunta.PreguntaEntry.CREAR_TABLA_PREGUNTA)
        db?.execSQL("INSERT INTO "+DBPregunta.PreguntaEntry.TABLE_NAME+
                "('"+DBPregunta.PreguntaEntry.COLUMN_ENUNCIADO+"', '"+DBPregunta.PreguntaEntry.COLUMN_SOLUCION+"', '"+DBPregunta.PreguntaEntry.COLUMN_TEMA+"', '"+DBPregunta.PreguntaEntry.COLUMN_ASIGNATURA+"', '"+DBPregunta.PreguntaEntry.COLUMN_NUMERO_ENUNCIADOS+"')" +
                "VALUES ('https://i.imgur.com/LPqtyIC.png', 'https://i.imgur.com/m1evQnv.png', 'Matrices', 'Matematicas', 2),"+//2
                "('https://i.imgur.com/UDFVL8U.png', 'https://i.imgur.com/i6gj1vs.png', 'Matrices', 'Matematicas', 3),"+//3
                "('https://i.imgur.com/nvKUjfg.png', 'https://i.imgur.com/lrDUT7P.png', 'Matrices', 'Matematicas', 3),"+//4
                "('https://i.imgur.com/ukmJxFp.png', 'https://i.imgur.com/E0DbT1H.png', 'Probabilidad', 'Matematicas', 1),"+//5
                "('https://i.imgur.com/HKp0NkL.png', 'https://i.imgur.com/H8hc8FW.png', 'Geometria', 'Matematicas', 2),"+//6
                "('https://i.imgur.com/ROHu7xH.png', 'https://i.imgur.com/s7Np2IB.png', 'Probabilidad', 'Matematicas', 2),"+//7
                "('https://i.imgur.com/ZWwJ495.png', 'https://i.imgur.com/pnZICGO.png', 'Matrices', 'Matematicas', 2)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


        db?.execSQL("DROP TABLE IF EXISTS " + DBPregunta.PreguntaEntry.TABLE_NAME +"")
        onCreate(db);
    }

    @SuppressLint("Range")
    fun preguntaPorId(idSelect:Int): PreguntaModel {

        val db = writableDatabase
        onUpgrade(db, 2,3)
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from Preguntas where "+DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+" = '"+idSelect+"'", null)
        } catch (e: SQLiteException) {
            db?.execSQL("CREATE TABLE " + DBPregunta.PreguntaEntry.TABLE_NAME + " (" +
                    DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+ " INTEGER NOT NULL UNIQUE," +
                    DBPregunta.PreguntaEntry.COLUMN_ENUNCIADO + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_SOLUCION + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_ASIGNATURA + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_TEMA + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_NUMERO_ENUNCIADOS + " INTEGER," +
                    "PRIMARY KEY( "+ DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+" AUTOINCREMENT)" +
                    ")"
            )
        }
        var id_Pregunta: Int
        var enunciado: String
        var solucion: String
        var asignatura: String
        var tema: String
        var numeroEnunciados: Int

        var pregunta = PreguntaModel(0, "enunciado", "solucion", "asignaturaSelect", "temaSelect", 0)
        if (cursor!!.moveToFirst()) {

                id_Pregunta = cursor.getInt(cursor.getColumnIndex(DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID))
                enunciado = cursor.getString(cursor.getColumnIndex(DBPregunta.PreguntaEntry.COLUMN_ENUNCIADO))
                solucion = cursor.getString(cursor.getColumnIndex(DBPregunta.PreguntaEntry.COLUMN_SOLUCION))
                asignatura = cursor.getString(cursor.getColumnIndex(DBPregunta.PreguntaEntry.COLUMN_ASIGNATURA))
                tema = cursor.getString(cursor.getColumnIndex(DBPregunta.PreguntaEntry.COLUMN_TEMA))
                numeroEnunciados = cursor.getInt(cursor.getColumnIndex(DBPregunta.PreguntaEntry.COLUMN_NUMERO_ENUNCIADOS))

            pregunta = PreguntaModel(id_Pregunta, enunciado, solucion, asignatura, tema, numeroEnunciados)



        }
        return pregunta
    }

    @SuppressLint("Range")
    fun listaId(asignaturaSelect: String, temaSelect: String): ArrayList<Int>{
        val arrayId = ArrayList<Int>()
        val db = writableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery("select "+DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+" from " + DBPregunta.PreguntaEntry.TABLE_NAME +" where TEMA ='"+temaSelect+"' AND Asignatura = '"+asignaturaSelect+"'", null)
        } catch (e: SQLiteException) {
            db?.execSQL("CREATE TABLE " + DBPregunta.PreguntaEntry.TABLE_NAME + " (" +
                    DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+ " INTEGER NOT NULL UNIQUE," +
                    DBPregunta.PreguntaEntry.COLUMN_ENUNCIADO + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_SOLUCION + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_ASIGNATURA + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_TEMA + " TEXT," +
                    DBPregunta.PreguntaEntry.COLUMN_NUMERO_ENUNCIADOS + " INTEGER," +
                    "PRIMARY KEY( "+ DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+" AUTOINCREMENT)" +
                    ")")
            return ArrayList()
        }
        var numId:Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                numId = cursor.getInt(cursor.getColumnIndex(DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID))


                arrayId.add(numId)
                cursor.moveToNext()
            }
        }
        return arrayId
    }



}