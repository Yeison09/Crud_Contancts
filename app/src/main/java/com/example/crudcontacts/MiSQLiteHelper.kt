package com.example.crudcontacts

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MiSQLiteHelper(context: Context) : SQLiteOpenHelper(
    context, "agenda.db", null, 4
) {


    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE agenda " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombreCompleto TEXT, apellido TEXT, numero TEXT, iniciales TEXT, correo TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS agenda"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }


    fun anayadirDato(
        nombreCompleto: String,
        apellido: String,
        numero: String,
        iniciales: String,
        correo: String
    ) {
        val datos = ContentValues()
        datos.put("nombreCompleto", nombreCompleto)
        datos.put("apellido", apellido)
        datos.put("numero", numero)
        datos.put("iniciales", iniciales)
        datos.put("correo", correo)

        val db = this.writableDatabase
        db.insert("agenda", null, datos)
        db.close()

    }

    fun actualizarDato(id: Int, nombre: String, apellido: String, numero: String, correo: String) {
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("nombreCompleto", nombre)
        datos.put("apellido", apellido)
        datos.put("numero", numero)
        datos.put("correo", correo)

        val db = this.writableDatabase
        db.update("agenda", datos, "id = ?", args)
        db.close()

    }


}