package com.tugas_akhir.tambal_ban.Activity.Account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.tugas_akhir.tambal_ban.API.Endpoints
import com.tugas_akhir.tambal_ban.R
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    lateinit var context : Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        context = this
        this.supportActionBar?.hide()
        btn_register.setOnClickListener { registerAction() }
    }

    fun registerAction(){
        val username = edt_username.text.toString().trim()
        val password = edt_password.text.toString().trim()
        val namauser = edt_namaUser.text.toString().trim()
        if (username.isEmpty()){
            edt_username.error = getString(R.string.error)
        }else if (password.isEmpty()){
            edt_password.error = getString(R.string.error)
        }else if (namauser.isEmpty()){
            edt_namaUser.error = getString(R.string.error)
        }else{
            register(username, password, namauser)
        }
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
        val req = JsonObjectRequest(Request.Method.POST, Endpoints.USER_REGISTER, jsonObject, {
            response ->
            try {
                Toast.makeText(this, "Akun anda sudah berhasil dibuat", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }catch ( e : JSONException){
                Toast.makeText(this, "Username atau Password anda salah", Toast.LENGTH_LONG).show()
                Log.e("error", e.toString())
            }
        }, {
            error ->
            Toast.makeText(this, "Username atau Password anda salah", Toast.LENGTH_LONG).show()
            Log.e("Error", error.toString())
        })
        que.add(req)
    }


}