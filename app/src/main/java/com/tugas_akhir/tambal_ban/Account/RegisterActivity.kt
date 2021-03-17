package com.tugas_akhir.tambal_ban.Account

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.tugas_akhir.tambal_ban.API.Endpoints
import com.tugas_akhir.tambal_ban.R
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    lateinit var context : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        context = this
        btn_register.setOnClickListener { registerAction() }
    }

    fun registerAction(){
        val username = edt_username.text.toString().trim()
        val password = edt_password.text.toString().trim()
        val namauser = edt_namaUser.text.toString().trim()
        register(username, password, namauser)
    }

    fun register(username : String, password : String, namaUser : String){
        val obj = JSONObject()
        obj.put("username", username)
        obj.put("password", password)
        obj.put("namauser", namaUser)
        registerForm(obj)
    }

    fun registerForm(jsonObject: JSONObject){
        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.POST, Endpoints.register, jsonObject, Response.Listener {
            response ->
            Log.e("mess", response.toString())
        }, Response.ErrorListener {
            error ->
            Log.e("Error", error.toString())
        })
        que.add(req)
    }


}