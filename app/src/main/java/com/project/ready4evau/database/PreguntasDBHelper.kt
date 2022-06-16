package com.example.ready4evau.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.project.ready4evau.database.DBExamen

class PreguntasDBHelper (context: Context): SQLiteOpenHelper(context, "Ready4Evau0.8.db", null, 2){
    override fun onCreate(db: SQLiteDatabase?) {


        db?.execSQL(DBPregunta.PreguntaEntry.CREAR_TABLA_PREGUNTA)

        db?.execSQL("INSERT INTO "+DBPregunta.PreguntaEntry.TABLE_NAME+
                "('"+DBPregunta.PreguntaEntry.COLUMN_ENUNCIADO+"', '"+DBPregunta.PreguntaEntry.COLUMN_SOLUCION+"', '"
                +DBPregunta.PreguntaEntry.COLUMN_TEMA+"', '"+DBPregunta.PreguntaEntry.COLUMN_ASIGNATURA+"', '"
                +DBPregunta.PreguntaEntry.COLUMN_NUMERO_ENUNCIADOS+"')" +
                "VALUES ('https://i.imgur.com/LPqtyIC.png', 'https://i.imgur.com/m1evQnv.png', 'Matrices', 'Matematicas', 2),"+//2
                "('https://i.imgur.com/UDFVL8U.png', 'https://i.imgur.com/i6gj1vs.png', 'Matrices', 'Matematicas', 3),"+//3
                "('https://i.imgur.com/nvKUjfg.png', 'https://i.imgur.com/lrDUT7P.png', 'Matrices', 'Matematicas', 3),"+//4
                "('https://i.imgur.com/nvKUjfg.png', 'https://i.imgur.com/lrDUT7P.png', 'Matrices', 'Matematicas', 3),"+//4
                "('https://i.imgur.com/ukmJxFp.png', 'https://i.imgur.com/E0DbT1H.png', 'Probabilidad', 'Matematicas', 1),"+//5
                "('https://i.imgur.com/HKp0NkL.png', 'https://i.imgur.com/H8hc8FW.png', 'Geometria', 'Matematicas', 2),"+//6
                "('https://i.imgur.com/ROHu7xH.png', 'https://i.imgur.com/s7Np2IB.png', 'Probabilidad', 'Matematicas', 2),"+//7
                "('https://i.imgur.com/ZWwJ495.png', 'https://i.imgur.com/pnZICGO.png', 'Matrices', 'Matematicas', 2)"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


        db?.execSQL("DROP TABLE IF EXISTS " + DBPregunta.PreguntaEntry.TABLE_NAME)
        //db?.execSQL("DROP TABLE IF EXISTS " + DBExamen.ExamenEntry.TABLE_NAME)
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
        cursor = db.rawQuery("select "+DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+" from " + DBPregunta.PreguntaEntry.TABLE_NAME +" where TEMA ='"+temaSelect+"' AND Asignatura = '"+asignaturaSelect+"'", null)

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

    @SuppressLint("Range")
    fun listaId(asignaturaSelect: String): ArrayList<Int>{
        val arrayId = ArrayList<Int>()
        val db = writableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery("select "+DBPregunta.PreguntaEntry.COLUMN_PREGUNTA_ID+" from " + DBPregunta.PreguntaEntry.TABLE_NAME +" where  Asignatura = '"+asignaturaSelect+"'", null)
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

    fun crearExamen(examen :ExamenModel){
        val db = writableDatabase

        try {
            db?.execSQL("INSERT INTO "+DBExamen.ExamenEntry.TABLE_NAME+
                    "('"+DBExamen.ExamenEntry.COLUMN_PREGUNTA1+"', '"+DBExamen.ExamenEntry.COLUMN_PREGUNTA2+"', '"+DBExamen.ExamenEntry.COLUMN_PREGUNTA3+"', '"+DBExamen.ExamenEntry.COLUMN_PREGUNTA4+"', '"+DBExamen.ExamenEntry.COLUMN_PREGUNTA5+"', '"+DBExamen.ExamenEntry.COLUMN_TEMA+"', '"+DBExamen.ExamenEntry.COLUMN_ASIGNATURA+"', '"+DBExamen.ExamenEntry.COLUMN_NOTA+"', '"+DBExamen.ExamenEntry.COLUMN_FECHA+"', '"+DBExamen.ExamenEntry.COLUMN_USER+"')" +
                    " VALUES ('"+examen.pregunta1+"', '"+examen.pregunta2+"', '"+examen.pregunta3+"', '"+examen.pregunta4+"', '"+examen.pregunta5+"', '"+examen.tema+"', '"+examen.asignatura+"', '"+examen.nota+"', '"+examen.fecha+"', '"+examen.user+"')")

        }catch (e: SQLiteException){
            db?.execSQL(DBExamen.ExamenEntry.CREAR_TABLA_EXAMEN)
        }

    }
    fun borrarUltimoExamen(){
        val db = writableDatabase


            db?.execSQL("DELETE FROM "+DBExamen.ExamenEntry.TABLE_NAME+" WHERE " +
                    ""+ DBExamen.ExamenEntry.COLUMN_EXAMEN_ID +" = (SELECT MAX("+ DBExamen.ExamenEntry.COLUMN_EXAMEN_ID +") " +
                    "FROM "+DBExamen.ExamenEntry.TABLE_NAME+" )")




    }
    fun cambiarNotaUltimoExamen(nota:String){
        val db = writableDatabase


        db?.execSQL("UPDATE "+DBExamen.ExamenEntry.TABLE_NAME+" SET " +DBExamen.ExamenEntry.COLUMN_NOTA+" = "+nota+
                " WHERE "+ DBExamen.ExamenEntry.COLUMN_EXAMEN_ID +" = (SELECT MAX("+ DBExamen.ExamenEntry.COLUMN_EXAMEN_ID +") " +
                "FROM "+DBExamen.ExamenEntry.TABLE_NAME+" )")




    }

    @SuppressLint("Range")
    fun selectAllExams(idUser: String): ArrayList<ExamenAdapterModel>{
        val al_examenes = ArrayList<ExamenAdapterModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBExamen.ExamenEntry.TABLE_NAME+ " WHERE "+ DBExamen.ExamenEntry.COLUMN_USER+" = '"+idUser+"'", null)
        } catch (e: SQLiteException) {
            return ArrayList()
        }

        var id: Int
        var asignatura: String
        var tema: String
        var nota: String
        var fecha: String
        var pregunta1: String
        var pregunta2: String
        var pregunta3: String
        var pregunta4: String
        var pregunta5: String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id= cursor.getInt(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_EXAMEN_ID))
                asignatura = cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_ASIGNATURA))
                tema = cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_TEMA))
                nota = cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_NOTA))
                fecha= cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_FECHA))
                pregunta1= cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_PREGUNTA1))
                pregunta2= cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_PREGUNTA2))
                pregunta3= cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_PREGUNTA3))
                pregunta4= cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_PREGUNTA4))
                pregunta5= cursor.getString(cursor.getColumnIndex(DBExamen.ExamenEntry.COLUMN_PREGUNTA5))

                al_examenes.add(ExamenAdapterModel(id,asignatura, tema, nota, fecha, pregunta1, pregunta2, pregunta3, pregunta4, pregunta5))
                cursor.moveToNext()
            }
        }
        return  al_examenes
    }
    fun borrarExamen(id : Int){
        val db = writableDatabase
        db?.execSQL("DELETE FROM "+DBExamen.ExamenEntry.TABLE_NAME+" WHERE " +
                ""+ DBExamen.ExamenEntry.COLUMN_EXAMEN_ID +" = '"+id+"'")
    }




}