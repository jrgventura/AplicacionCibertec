package com.example.aplicacioncibertec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private lateinit var firestore: FirebaseFirestore
    val listUsuarios = arrayListOf<UserFirestore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap)
                as SupportMapFragment
        mapFragment.getMapAsync(this)

        firestore = FirebaseFirestore.getInstance()
        firestore.collection("usuarios").get()
            .addOnSuccessListener { documentList ->

                for (document in documentList) {
                    val nombre = document.getString("name")
                    val apellido = document.getString("lastname")
                    val correo = document.getString("email")

                    if (nombre != null && apellido != null && correo != null) {

                        val usuario = UserFirestore(nombre, apellido, correo)
                        listUsuarios.add(usuario)

                    }

                }

            }


    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        val positionMarker = LatLng(-8.1116778, -79.0287742)
        map.addMarker(
            MarkerOptions()
                .position(positionMarker)
                .title("Ciudad de Trujillo")
        )
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(positionMarker, 18f),
            4000, null
        )

    }

}