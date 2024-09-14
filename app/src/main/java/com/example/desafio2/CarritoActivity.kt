package com.example.desafio2

import Carrito
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
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
    }
}
