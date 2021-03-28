package com.tugas_akhir.tambal_ban.Activity.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tugas_akhir.tambal_ban.API.Endpoints
import com.tugas_akhir.tambal_ban.R
import com.tugas_akhir.tambal_ban.Util.Collection
import kotlinx.android.synthetic.main.activity_detail.*
import org.json.JSONException

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var context: Context
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        context = this
        this.supportActionBar?.hide()
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        data()
    }
    override fun onMapReady(googleMap: GoogleMap?) {
        val lat = intent.getStringExtra("latitude").toDouble()
        val lng = intent.getStringExtra("longitude").toDouble()
        if (googleMap != null) {
            mMap = googleMap
        }
        val location = LatLng(lat,lng)
        mMap.addMarker(MarkerOptions().position(location).title("Lokasi"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f))
        mMap.uiSettings.isRotateGesturesEnabled = false
        mMap.uiSettings.isZoomGesturesEnabled = false
        mMap.uiSettings.setAllGesturesEnabled(false)
    }

    private fun data(){
        val id = intent.getStringExtra("id")
        val lat = intent.getStringExtra("latitude")
        val lng = intent.getStringExtra("longitude")
        Log.e("mess", lat +"/"+lng)
        getDetail(id)

        btn_booking.setOnClickListener {
            val intent = Intent(this, BookingServiceActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }


    private fun getDetail(id : String){
        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.GET, Endpoints.getListDetail+id, null, {
            response ->
            try {
                Log.e("mess", response.toString())
                edt_name.text = response.getString("nama_tambal_ban")
                edt_address.text = response.getString("alamat")
                edt_hour.text = response.getString("jam_operasi")
                edt_description.text = response.getString("deskripsi")
                edt_price.text = response.getString("detail_harga")

            }catch (e : JSONException){
                Log.e("ERROR", e.toString())
            }
        }, {
            error ->
            Log.e("mess", error.toString())
        })
        que.add(req)
    }
}