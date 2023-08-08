package com.example.crudcontacts

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.ShapeDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudcontacts.databinding.ActivityCrearContactoBinding
import com.example.crudcontacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private lateinit var recyclerViewAgenda: RecyclerViewAgenda
    private lateinit var binding: ActivityMainBinding
    private lateinit var agendaDBHelper: MiSQLiteHelper
    private lateinit var db: SQLiteDatabase





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        var toolbar = binding.toolbar
        setSupportActionBar(toolbar)


        agendaDBHelper = MiSQLiteHelper(this)
        db = agendaDBHelper.readableDatabase


        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM agenda", null
        )
        val adaptador = RecyclerViewAgenda()

        adaptador.RecyclerViewAgenda(this, cursor)

        binding.recyclerMostrar.setHasFixedSize(true)
        binding.recyclerMostrar.layoutManager = LinearLayoutManager(this)
        binding.recyclerMostrar.adapter = adaptador

    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       // return super.onCreateOptionsMenu(menu)
      menuInflater.inflate(R.menu.toolbar_menuprincipal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.AgregarPersona -> {
                val intent = Intent(this, CrearContactoActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.Ajustes ->{
                val intent = Intent(this, MostrarContactoActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }












}