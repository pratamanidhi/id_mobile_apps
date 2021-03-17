package com.tugas_akhir.tambal_ban.Account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.tugas_akhir.tambal_ban.API.Endpoints
import com.tugas_akhir.tambal_ban.R
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        context = this
        this.supportActionBar?.hide()
        btn_login.setOnClickListener { getLogin() }
        getRegister()
    }

    fun  getLogin(){
        val username = edt_username.text.toString()
        val password = edt_password.text.toString()
        login(username,password)
    }

    fun getRegister(){
        edt_register.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(username : String, password : String){
        val que = Volley.newRequestQueue(this)
        val req = object : StringRequest(Request.Method.POST,Endpoints.login, Response.Listener {
            response ->
            val jsonObj = JSONObject(response)
            Log.e("mess", jsonObj.toString())
        }, Response.ErrorListener {
            error ->
            Log.e("error", error.toString())
        }){
            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("username", username)
                params.put("password", password)
                return params
            }
        }
        que.add(req)

    }
}
