package com.tugas_akhir.tambal_ban.Activity.HomePage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.tugas_akhir.tambal_ban.API.Endpoints
import com.tugas_akhir.tambal_ban.Activity.Account.LoginActivity
import com.tugas_akhir.tambal_ban.Adapter.TambalBanAdapter
import com.tugas_akhir.tambal_ban.Model.TambalBanModel
import com.tugas_akhir.tambal_ban.R
import kotlinx.android.synthetic.main.activity_home_page.*
import org.json.JSONException

class HomePage : AppCompatActivity() {
    lateinit var context : Context
    lateinit var adapter : TambalBanAdapter
    var list : ArrayList<TambalBanModel> = ArrayList()
    lateinit var shp : SharedPreferences
    lateinit var shpEditor: SharedPreferences.Editor
    val login = LoginActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        context = this
        this.supportActionBar?.hide()
        getListOfTB()
//        shp = this.getSharedPreferences(login.my_shared_preferences, Context.MODE_PRIVATE)
//        isLogout()
    }


//    fun isLogout(){
//        btn_logout.setOnClickListener {
//            clearSession()
//        }
//    }
//
//    fun clearSession(){
//        shpEditor = shp.edit()
//        shpEditor.putBoolean(login.sessionStatus, false)
//        shpEditor.commit()
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish()
//    }

    fun getListOfTB(){
        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.GET,Endpoints.getListData, null, Response.Listener {
            response ->
            try {
                val body = response.getJSONArray("body")
                for (i in 0 until body.length()){
                    val item = body.getJSONObject(i)
                    val id = item.getString("id_tambal_ban")
                    val name = item.getString("nama_tambal_ban")
                    val addess = item.getString("alamat")
                    val price = item.getString("harga")
                    val operation = item.getString("jam_operasi")
                    val latitude = item.getString("latitude")
                    val longitude = item.getString("longitude")
                    list.add(
                            TambalBanModel(
                                    id,
                                    name,
                                    addess,
                                    price,
                                    operation,
                                    latitude,
                                    longitude
                            )
                    )
                    rv_orders.layoutManager = LinearLayoutManager(context)
                    adapter = TambalBanAdapter(context, list)
                    rv_orders.adapter = adapter
                }
            }catch (e : JSONException){
                e.printStackTrace()
            }
        }, Response.ErrorListener {
            error ->
        })
        que.add(req)
    }
}