package com.example.desafio2

import SelectProductActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val usuariosValidos = mapOf(
        "user" to "123",
        "user2" to "password2",
        "user3" to "password3",
        "user4" to "password4",
        "user5" to "password5"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val etUsername: EditText = findViewById(R.id.etUsername)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (usuariosValidos[username] == password) {
            // Login exitoso, redirigir a la pantalla de selección de productos
            val intent = Intent(this, SelectProductActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Mostrar mensaje de error
            etPassword.error = "Credenciales incorrectas"
        }
    }
}
