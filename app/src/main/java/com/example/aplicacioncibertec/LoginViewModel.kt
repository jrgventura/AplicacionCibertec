package com.example.aplicacioncibertec

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplicacioncibertec.network.LoginResponse
import com.example.aplicacioncibertec.network.UsersResponse
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginViewModel: ViewModel() {

    private val repository= LoginRepository()

    private lateinit var auth: FirebaseAuth
    val userLoginServiceResponse = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()

    fun login(email: String, pass: String) {
        // loginFirebase(email, pass)
        loginRetrofit(email, pass)
    }

    private fun loginFirebase(email: String, pass: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->
                userLoginServiceResponse.value = task.isSuccessful
            }
    }

    fun recuperar(email: String) {
        recuperarClave(email)
    }

    // Recuperar Contraseña
    private fun recuperarClave(email: String) {
        auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(Activity()) { task ->

            }
    }

    // Login Retrofit
    private fun loginRetrofit(email: String, pass: String) {
        disposable.add(
            repository.login(email, pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<LoginResponse>(){

                    override fun onSuccess(t: LoginResponse) {
                        val token = t.token
                        userLoginServiceResponse.value = true
                    }

                    override fun onError(e: Throwable) {
                        userLoginServiceResponse.value = false
                        if(e is HttpException) {
                            val codeError = e.code() // 400 401 500 502 etc etc
                        }
                    }
                })
        )
    }

    private fun listUserRetrofit(){
        disposable.add(
            repository.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<UsersResponse>(){
                    override fun onSuccess(t: UsersResponse) {
                        Log.v("USUARIOS", t.data.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.v("ERROR", e.message.toString())
                    }
                })
        )
    }

}