package com.example.crudcontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudcontacts.databinding.ActivityMostrarContactoBinding

class MostrarContactoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarContactoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarContactoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre = intent.getStringExtra("nombre") ?: "Nombre no encontrado"
        var nombreAgenda = binding.nombreAgenda
        nombreAgenda.text = nombre

        var apellido = intent.getStringExtra("apellido") ?: "Apellido no encontrado"
        var apellidoAgenda = binding.apellidoAgenda
        apellidoAgenda.text = apellido

        val numero = intent.getStringExtra("numero") ?: "Numero no encontrado"
        var telefono = binding.telefono
        telefono.text = numero

        val correo = intent.getStringExtra("correo") ?: "Correo no encontrado"
        var correoAgenda = binding.editTextCorreoElectronico
        correoAgenda.text = correo

        val id = intent.getStringExtra("id") ?: "Id no encontrado"


        binding.btnEditar.setOnClickListener {
            val miIntentDos = Intent(this, ModificarDatos::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("apellido", apellido)
                putExtra("numero", numero)
                putExtra("correo", correo)
                putExtra("id", id)
            }
            startActivity(miIntentDos)
        }


    }
}