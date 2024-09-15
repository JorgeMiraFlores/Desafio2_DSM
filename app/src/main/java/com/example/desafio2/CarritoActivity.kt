package com.example.desafio2

import Carrito
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarritoActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carrito)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtén el carrito de la Intent
        val carrito = intent.getParcelableExtra<Carrito>("carrito") ?: return

        // Muestra los productos en el carrito
        val tvCarritoContenido: TextView = findViewById(R.id.tvCarritoContenido)
        tvCarritoContenido.text = carrito.obtenerProductos().joinToString(separator = "\n") {
            "${it.nombre}: $${it.precio}"
        }

        // Muestra el total
        val tvTotal: TextView = findViewById(R.id.tvTotal)
        tvTotal.text = "Total: $${carrito.calcularTotal()}"

        // Maneja el clic en el botón "Comprar"
        val btnComprar: Button = findViewById(R.id.btnComprar)
        btnComprar.setOnClickListener {
            // Guarda los datos de la compra
            guardarCompra(carrito)

            // Vacía el carrito después de la compra
            carrito.vaciarCarrito()

            // Actualiza la interfaz
            tvCarritoContenido.text = ""
            tvTotal.text = "Total: $0.0"

            // Muestra un mensaje de éxito
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()

            // Navega a la pantalla de historial de compras
            val intent = Intent(this, HistorialComprasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun guardarCompra(carrito: Carrito) {
        // Implementa la lógica para guardar la compra
        val sharedPreferences = getSharedPreferences("Compras", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val productos = carrito.obtenerProductos().joinToString(separator = ", ") {
            "${it.nombre}: $${it.precio}"
        }
        val total = carrito.calcularTotal()

        val historial = sharedPreferences.getString("historial", "") ?: ""
        val nuevaCompra = "Compra: $productos - Total: $$total\n"
        editor.putString("historial", historial + nuevaCompra)
        editor.apply()
    }
}
