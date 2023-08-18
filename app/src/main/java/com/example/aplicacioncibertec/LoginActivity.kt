package com.example.aplicacioncibertec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class LoginActivity: AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        val edtCorreo =
            findViewById<TextInputEditText>(R.id.edtCorreo)
        val edtClave =
            findViewById<TextInputEditText>(R.id.edtClave)

        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            val correo = edtCorreo.text.toString()
            val clave = edtClave.text.toString()
            viewModel.login(correo, clave)
        }

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewModel.userLoginServiceResponse.observe(this) {
            if (it) {
                // Login correcto
                startActivity(Intent(this,
                    MainActivity::class.java))
            } else {
                // Mostramos mensaje de error
            }
        }

        val btnRecuperar = findViewById<Button>(R.id.btnRecuperar)
        btnRecuperar.setOnClickListener {
            val correo = edtCorreo.text.toString()
            viewModel.recuperar(correo)
        }
    }

}