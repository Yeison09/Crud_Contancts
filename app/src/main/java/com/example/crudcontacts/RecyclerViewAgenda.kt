package com.example.crudcontacts

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.crudcontacts.databinding.ItemRecyclerviewBinding
import java.util.*
import kotlin.random.Random.Default.nextInt

class RecyclerViewAgenda(): RecyclerView.Adapter<RecyclerViewAgenda.ViewHolder>(){

    lateinit var context: Context
    lateinit var cursor: Cursor


    fun RecyclerViewAgenda(context: Context, cursor: Cursor){
        this.context = context
        this.cursor = cursor
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_recyclerview,parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
       holder.id.text = cursor.getString(0)
        holder.editTextTextPersonName.text = cursor.getString(1)
        holder.editTextTextPersonName3.text = cursor.getString(2)
        holder.editTextPhone2.text = cursor.getString(3)
        holder.iniciales.text = cursor.getString(4)
        holder.editTextCorreo.text = cursor.getString(5)
        val random = Random()
        val colores = intArrayOf(R.color.azul, R.color.verde, R.color.rosado, R.color.mamey,R.color.morado,R.color.amarillo,R.color.rojo)
        val randomNumber = random.nextInt(colores.size)


        holder.color.backgroundTintList =  ContextCompat.getColorStateList(context, colores[randomNumber])

    }

    override fun getItemCount(): Int {
        if(cursor == null)
            return 0
        else
            return  cursor.count
    }



    inner class ViewHolder: RecyclerView.ViewHolder{
        val editTextTextPersonName: TextView
        val editTextTextPersonName3: TextView
        val editTextPhone2: TextView
       val iniciales: TextView
       val editTextCorreo: TextView
        var color: ImageButton
       var id: TextView





        constructor(view: View): super(view){
            val bindingItemsRV = ItemRecyclerviewBinding.bind(view)
            editTextTextPersonName = bindingItemsRV.textNombre
            editTextTextPersonName3 = bindingItemsRV.textApellido
            editTextPhone2 = bindingItemsRV.textNumero
            iniciales = bindingItemsRV.textIniciales
            editTextCorreo = bindingItemsRV.textCorreo
            color = bindingItemsRV.circulo
            id = bindingItemsRV.textId
            val contexto: Context = view.context

            view.setOnClickListener {
                Toast.makeText(context,
                    "${id.text}, ${editTextTextPersonName.text}, ${editTextTextPersonName3.text}, ${editTextPhone2.text}, ${editTextCorreo.text}",
                    Toast.LENGTH_SHORT).show()

                var nombreCompleto = "${editTextTextPersonName.text}"

                val miIntent = Intent(contexto, MostrarContactoActivity::class.java).apply{
                    putExtra("nombre", nombreCompleto)
                    putExtra("apellido", editTextTextPersonName3.text)
                    putExtra("numero", editTextPhone2.text)
                    putExtra("correo",editTextCorreo.text)
                    putExtra("id", id.text)
                }

                contexto.startActivity(miIntent)

            }
        }

    }



}