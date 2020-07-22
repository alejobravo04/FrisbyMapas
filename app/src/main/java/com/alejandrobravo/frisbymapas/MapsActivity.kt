package com.alejandrobravo.frisbymapas

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPoiClickListener {

    private lateinit var mMap: GoogleMap //instancia clase google maps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {  //recibe el mapa que me manda google

        activarMyLocation()

        mMap = googleMap //se lo asignamos al componente que habiamos creado

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL //tipo de mapa

        mMap.uiSettings.isZoomControlsEnabled = true //Coloca el + y - del zoom

        mMap.setOnPoiClickListener(this)

        /*val frisbyFlorida = LatLng(6.2727595,-75.5776327)
        mMap.addMarker(MarkerOptions().position(frisbyFlorida).title("Frisby C.C. Florida"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(frisbyFlorida))


        val frisbyManrique = LatLng(6.2662108,-75.5621025)
        mMap.addMarker(MarkerOptions().position(frisbyManrique).title("Frisby Manrique"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(frisbyManrique))

        val frisbyExitoColombia = LatLng(6.2633445,-75.5667864)
        mMap.addMarker(MarkerOptions().position(frisbyExitoColombia).title("Frisby Exito - Colombia"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(frisbyExitoColombia))


        val frisbyJumbo65 = LatLng(6.2602986,-75.5786106)
        mMap.addMarker(MarkerOptions().position(frisbyJumbo65).title("Frisby Jumbo de la 65"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(frisbyJumbo65))

        val frisbySanDiego = LatLng(6.2375548,-75.5780888)
        mMap.addMarker(MarkerOptions().position(frisbySanDiego).title("Frisby San Diego"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(frisbySanDiego))*/

        val frisbyAventura = LatLng(6.2633445,-75.5667864)
        mMap.addMarker(MarkerOptions().position(frisbyAventura).title("Frisby C.C. Aventura"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(frisbyAventura,13F))


    }

    private fun activarMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap.isMyLocationEnabled = true
    }

    override fun onPoiClick(poi: PointOfInterest?) {

        Toast.makeText(this,"Nombre: ${poi?.name},  Latitud : ${poi?.latLng?.latitude}, Longitud : ${poi?.latLng?.longitude}\"" ,Toast.LENGTH_SHORT).show()

    }
}