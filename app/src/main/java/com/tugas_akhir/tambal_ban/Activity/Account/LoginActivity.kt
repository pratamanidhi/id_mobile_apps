package com.tugas_akhir.tambal_ban.Activity.Account

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.tugas_akhir.tambal_ban.API.Endpoints
import com.tugas_akhir.tambal_ban.Activity.HomePage.HomePage
import com.tugas_akhir.tambal_ban.R
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var context : Context
    lateinit var shp : SharedPreferences
    lateinit var shpEditor: SharedPreferences.Editor
    var username:String=""
    var password:String=""
    public var session:Boolean = false
    var cst_id : String = ""
    val sessionStatus:String = "session_status"
    val TAG_USERNAME:String = "username"
    val TAG_CST_ID:String = "cst_id"
    val my_shared_preferences:String = "my_shared_preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        context = this
        this.supportActionBar?.hide()
        btn_login.setOnClickListener { getLogin() }
        getRegister()
        isLogin()
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

    fun isLogin(){
        shp = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE)
        session = shp.getBoolean(sessionStatus, false)
        cst_id = shp.getString(TAG_CST_ID, null).toString()
        if (session){
            val intent = Intent(this, HomePage::class.java)
            intent.putExtra(TAG_CST_ID, cst_id)
            startActivity(intent)
            finish()
        }
    }

    fun login(username : String, password : String){
        val obj = JSONObject()
        obj.put("username", username)
        obj.put("password", password)

        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.POST,Endpoints.USER_LOGIN, obj,{
            response ->
            try {
                    cst_id = response.getInt("id_user").toString()
                    shpEditor = shp.edit()
                    shpEditor.putBoolean(sessionStatus,true)
                    shpEditor.putString(TAG_CST_ID, cst_id)
                    shpEditor.commit()
                    val intent = Intent(this, HomePage::class.java)
                    intent.putExtra(TAG_CST_ID, cst_id)
                    startActivity(intent)
                    finish()
            }catch (e : JSONException){
                Log.e("ERROR", e.toString())
                Toast.makeText(this, "Username atau Password anda salah", Toast.LENGTH_LONG).show()
            }
        },{
            error ->
            Log.e("error", error.toString())
            Toast.makeText(this, "Username atau Password anda salah", Toast.LENGTH_LONG).show()
        })
        que.add(req)
    }
}
