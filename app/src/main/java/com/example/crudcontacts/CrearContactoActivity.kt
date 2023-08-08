package com.example.crudcontacts


import android.content.res.ColorStateList
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources.Theme
import android.database.sqlite.SQLiteDatabase


import android.os.Build
import androidx.core.content.res.ResourcesCompat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.view.ContextThemeWrapper
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

import com.example.crudcontacts.databinding.ActivityCrearContactoBinding



class CrearContactoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearContactoBinding
    lateinit var agendaDBHelper: MiSQLiteHelper
    private lateinit var db: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearContactoBinding.inflate(layoutInflater)
        setContentView(binding.root)



        agendaDBHelper = MiSQLiteHelper(this)
/*
   Estoy llamando a la funcion onOptionsItemSelected, que esta abajo. pero lo tengo comentado
   porque ya tengo setOnMenuItemClickListern { menuIte -> directamente en la linea 33

        binding.toolbar.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
      }
*/

        //Esta parte aqui es para cuando se le de a la x vaya atras, pero esto funciona con la activity
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.guardar.setOnClickListener {

            if (binding.editTextTextPersonName.text.isNotBlank() && binding.editTextTextPersonName3.text.isNotBlank() && binding.editTextPhone2.text.isNotBlank()) {

                var primerCaracter = binding.editTextTextPersonName.text.toString().first().uppercase()
                var segundoCaracter = binding.editTextTextPersonName3.text.toString().first().uppercase()
                var iniciales = primerCaracter+segundoCaracter


                /*
                val theme = ContextThemeWrapper(this, R.style.Theme_CrudContacts).theme
                val color = ContextCompat.getColor(this, R.color.verde)
                theme.applyStyle(R.style.NuevoEstilo, true)
                window.decorView.setBackgroundColor(color) */


                /*
                val colorNuevo = "#FF0000" // Nueva definición de color en formato hexadecimal
                val colorId = resources.getIdentifier("verdeCirculo", "color", packageName) // Obtiene el ID del color a sustituir
                resources.setColor(colorId, Color.parseColor(colorNuevo)) // Sustituye el color con el nuevo valor
                */


                /*
                val resources: Resources = context.resources

                val colorNuevo = 0xFF123456.toInt() // Reemplaza 0xFF123456 con el valor hexadecimal del nuevo color
                val colorId = resources.getIdentifier("verdeCirculo", "color", packageName) // Reemplaza "verdeCirculo" con el nombre del color que deseas cambiar
                ResourcesCompat.getColor(resources, colorId, null)?.let { resources.setColor(colorId, colorNuevo, null) }
                */

                


                agendaDBHelper.anayadirDato(binding.editTextTextPersonName.text.toString(), binding.editTextTextPersonName3.text.toString(), binding.editTextPhone2.text.toString(), iniciales, binding.editTextCorreo.text.toString())

                binding.editTextTextPersonName.text.clear()
                binding.editTextPhone2.text.clear()
                binding.editTextTextPersonName3.text.clear()

                Toast.makeText(this, "Se ha guardado los datos ${iniciales}",  Toast.LENGTH_LONG).show()
               // return@setOnClickListener true
            }else{
                Toast.makeText(this, "No se guardo", Toast.LENGTH_LONG).show()
            }


            val intentRecyclerView = Intent(this, MainActivity::class.java)
            startActivity(intentRecyclerView)


        }

            binding.toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_guardar -> {
/*
                    if(binding.editTextTextPersonName.text.isNotBlank() && binding.editTextPhone2.text.isNotBlank()){
                        agendaDBHelper.anayadirDato(binding.editTextTextPersonName.text.toString(), binding.editTextPhone2.text.toString())

                        binding.editTextTextPersonName.text.clear()
                        binding.editTextPhone2.text.clear()

                        Toast.makeText(this, "Se ha guardado los datos", Toast.LENGTH_LONG).show()
                    } else{
                        //Toast aqui es mostrar un mensaje sino se guardo
                        Toast.makeText(this,"No se ha podido guarda",
                            Toast.LENGTH_LONG).show()
                    }

*/

                        Toast.makeText(this, "Se ha guardado los datos", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> super.onOptionsItemSelected(menuItem)
                }
            }


        }
/*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.nav_guardar -> {
                Toast.makeText(this, "Se ha guardado los datos", Toast.LENGTH_LONG).show()
                return true
            }

            else -> super.onOptionsItemSelected(menuItem)
        }
    }
    */


}