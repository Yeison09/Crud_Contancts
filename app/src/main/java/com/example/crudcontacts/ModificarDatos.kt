package com.example.crudcontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.crudcontacts.databinding.ActivityModificarDatosBinding

class ModificarDatos : AppCompatActivity() {

    private lateinit var binding: ActivityModificarDatosBinding
    lateinit var agendaDBHelper: MiSQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        agendaDBHelper = MiSQLiteHelper(this)

        var nombreModificar = intent.getStringExtra("nombre") ?: "Nombre no encontrado"
        var nombreAgenda = binding.editTextNombreModificar
        val editableNombre: Editable = Editable.Factory.getInstance().newEditable(nombreModificar)
        nombreAgenda.text = editableNombre

        var apellidoModificar = intent.getStringExtra("apellido") ?: "Apellido no encontrado"
        var apellido = binding.editTextApellidoModificar
        val editableApellido: Editable =
            Editable.Factory.getInstance().newEditable(apellidoModificar)
        apellido.text = editableApellido

        val numeroModificar = intent.getStringExtra("numero") ?: "Numero no encontrado"
        var telefono = binding.editTextPhoneModificar
        val editableNumero: Editable = Editable.Factory.getInstance().newEditable(numeroModificar)
        telefono.text = editableNumero

        val correoModificar = intent.getStringExtra("correo") ?: "Correo no encontrado"
        var correoAgenda = binding.editTextCorreoModificar
        val editableCorreo: Editable = Editable.Factory.getInstance().newEditable(correoModificar)
        correoAgenda.text = editableCorreo

        val idDeModificar = intent.getStringExtra("id") ?: "Id no encontrado"
        var idAgenda = binding.textIdModificar
        val editableId: Editable = Editable.Factory.getInstance().newEditable(idDeModificar)
        idAgenda.text = editableId.toString()


        binding.btnModifcar.setOnClickListener {
            if (binding.editTextNombreModificar.text.isNotBlank() && binding.editTextApellidoModificar.text.isNotBlank() && binding.editTextPhoneModificar.text.isNotBlank() && binding.editTextCorreoModificar.text.isNotBlank()) {
                agendaDBHelper.actualizarDato(
                    binding.textIdModificar.text.toString().toInt(),
                    binding.editTextNombreModificar.text.toString(),
                    binding.editTextApellidoModificar.text.toString(),
                    binding.editTextPhoneModificar.text.toString(),
                    binding.editTextCorreoModificar.text.toString()
                )

                binding.editTextNombreModificar.text.clear()
                binding.editTextApellidoModificar.text.clear()
                binding.editTextPhoneModificar.text.clear()
                binding.editTextCorreoModificar.text.clear()


                Toast.makeText(
                    this, "Modificado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this, "No se ha podido modificar",
                    Toast.LENGTH_LONG
                ).show()

            }

        }


    }
}