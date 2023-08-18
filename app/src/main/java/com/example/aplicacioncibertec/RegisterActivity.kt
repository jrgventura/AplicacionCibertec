package com.example.aplicacioncibertec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity: AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        val edtName = findViewById<TextInputEditText>(R.id.edtRegisterName)
        val edtLastname = findViewById<TextInputEditText>(R.id.edtRegisterLastname)
        val edtEmail = findViewById<TextInputEditText>(R.id.edtRegisterEmail)
        val edtPass = findViewById<TextInputEditText>(R.id.edtRegisterPass)
        val edtPassRepeat = findViewById<TextInputEditText>(R.id.edtRegisterRepeatPass)
        val btnRegister = findViewById<Button>(R.id.btnRegisterFirebase)

        btnRegister.setOnClickListener {
            val name = edtName.text.toString()
            val lastname = edtLastname.text.toString()
            val email = edtEmail.text.toString()
            val pass = edtPass.text.toString()
            val passRep = edtPassRepeat.text.toString()

            viewModel.register(email, pass, passRep, name, lastname)
        }

        viewModel.userRegisterFirebase.observe(this) {
            if (it) {
                // startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Verique sus credenciales",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

}