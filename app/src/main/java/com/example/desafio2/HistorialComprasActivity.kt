package com.example.desafio2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistorialComprasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial_compras)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtén el historial de compras
        val sharedPreferences = getSharedPreferences("Compras", MODE_PRIVATE)
        val historial = sharedPreferences.getString("historial", "")?.split("\n")?.filter { it.isNotEmpty() } ?: emptyList()

        // Configura el ListView
        val listView: ListView = findViewById(R.id.listViewHistorial)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historial)
        listView.adapter = adapter
    }
}
