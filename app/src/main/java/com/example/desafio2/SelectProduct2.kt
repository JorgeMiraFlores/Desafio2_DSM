package com.example.desafio2

import Carrito
import Producto
import ProductoAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ListView

class SelectProduct2 : AppCompatActivity() {
    private lateinit var carrito: Carrito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa el carrito
        carrito = Carrito()

        // Configura la lista de productos
        val productos = listOf(
            Producto(1, "Paracetamol", 5.0),
            Producto(2, "Ibuprofeno", 7.0),
            Producto(3, "Amoxicilina", 12.0),
            Producto(4, "Cetirizina", 10.0),
            Producto(5, "Omeprazol", 8.0),
            Producto(6, "Lorazepam", 15.0),
            Producto(7, "Dipirona", 6.0),
            Producto(8, "Dextrometorfano", 11.0),
            Producto(9, "Metformina", 9.0),
            Producto(10, "Paroxetina", 14.0)
        )

        // Configura el adaptador
        val adapter = ProductoAdapter(this, productos) { producto ->
            carrito.agregarProducto(producto)
        }

        // Configura el ListView
        val listView: ListView = findViewById(R.id.listViewProducts)
        listView.adapter = adapter

        // Configura el botón de ver carrito
        val btnVerCarrito: Button = findViewById(R.id.btnVerCarrito)
        btnVerCarrito.setOnClickListener {
            // Llama a la actividad del carrito aquí
            val intent = Intent(this, CarritoActivity::class.java)
            intent.putExtra("carrito", carrito)  // Asumiendo que Carrito implementa Parcelable o Serializable
            startActivity(intent)
        }

        // Configura el botón "Ver Historial de Compras"
        val btnVerHistorial: Button = findViewById(R.id.btnVerHistorial)
        btnVerHistorial.setOnClickListener {
            val intent = Intent(this, HistorialComprasActivity::class.java)
            startActivity(intent)
        }
    }
}
