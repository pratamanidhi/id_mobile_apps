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
        val que = Volley.newRequestQueue(this)
        val req = object : StringRequest(Request.Method.POST,Endpoints.login, Response.Listener {
            response ->
            try {
                val jsonObj = JSONObject(response)
                val loginMessage = jsonObj.getString("message")
                if (loginMessage=="Login successfull"){
                    val user = jsonObj.getJSONObject("user")
                    cst_id = user.getInt("id").toString()
                    shpEditor = shp.edit()
                    shpEditor.putBoolean(sessionStatus,true)
                    shpEditor.putString(TAG_CST_ID, cst_id)
                    shpEditor.commit()
                    val intent = Intent(this, HomePage::class.java)
                    intent.putExtra(TAG_CST_ID, cst_id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show()
                }
            }catch (e : JSONException){
                Log.e("ERROR", e.toString())
            }
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
