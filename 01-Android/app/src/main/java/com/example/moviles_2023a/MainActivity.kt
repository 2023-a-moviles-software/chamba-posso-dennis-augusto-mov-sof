package com.example.moviles_2023a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {



    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    // logica negocio
                    val data = result.data
                    "${data?.getStringExtra("nombreModificado")}"
                }
            }
        }



    val callbackIntentPickUri = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode === RESULT_OK){
            if(result.data != null){
                if(result.data!!.data != null){
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(uri, null, null, null, null, null)
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(indiceTelefono!!)
                    cursor?.close()
                    "Telefono ${telefono}"
                }
                //Lógica del negocio
                val uri = result.data?.data
                val intentConRespuesta = Intent()
                intentConRespuesta.putExtra("uri",uri.toString())
                setResult(
                    RESULT_OK,
                    intentConRespuesta
                )
                finish()
            }
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //base de datos sqLite
        EBaseDatos.tablaEntrenador = ESqliteHelperEntrenador(this)


        //Se crea una variable para el botón ciclo de vida
        val botonCicloVida = findViewById<Button>(
            R.id.btn_Ciclo_vida
        )
        //Se añade un listener al botón ciclo de vida
        botonCicloVida.setOnClickListener{
            irActividad(AACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )

        botonListView.setOnClickListener{
            irActividad(BListView::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)

        botonIntentImplicito.setOnClickListener{
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackIntentPickUri.launch(intentConRespuesta)
        }

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }
    }

    fun irActividad( clase: Class<*>){
        val intent = Intent(this, clase)
        //No recibimos respuesta
        startActivity(intent)
        // this.starActivity()
    }


    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        //enviar parametros
        // (aceptamos primitivos)
        intentExplicito.putExtra("nombre", "adrian")
        intentExplicito.putExtra("apellido", "eguez")
        intentExplicito.putExtra("edad", 30)
        //enviamos el intent con RESPUESTA
        //recibimos respuesta
        callbackContenidoIntentExplicito.launch(intentExplicito)

    }
}