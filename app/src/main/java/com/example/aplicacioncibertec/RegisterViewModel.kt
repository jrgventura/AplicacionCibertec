package com.example.aplicacioncibertec

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    val userRegisterFirebase = MutableLiveData<Boolean>()

    fun register(email: String, pass: String, repPass: String,
                 name: String, lastname: String) {
        registerFirebase(email, pass, name, lastname)
    }

    private fun registerFirebase(email: String, pass: String, name: String,
                                 lastname: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid
                    if (userId != null) {
                        registerFirestore(userId, email, name, lastname)
                    }
                } else {
                    userRegisterFirebase.value = false
                }
            }
    }

    private fun registerFirestore(id: String, email: String, name: String,
                                  lastname: String) {
        val usuario = UserFirestore(name, lastname, email)
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("usuarios").document(id).set(usuario)
            .addOnCompleteListener(Activity()) { task ->
                userRegisterFirebase.value = task.isSuccessful
            }
    }
}

data class UserFirestore(
    var name: String,
    var lastname: String,
    var email: String
)

