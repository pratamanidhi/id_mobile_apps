package com.tugas_akhir.tambal_ban.Activity.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.tugas_akhir.tambal_ban.API.Endpoints
import com.tugas_akhir.tambal_ban.R
import com.tugas_akhir.tambal_ban.Util.Collection.Companion.showCalendar
import com.tugas_akhir.tambal_ban.Util.Collection.Companion.showTimePicker
import kotlinx.android.synthetic.main.activity_booking_input.*
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class BookingServiceActivity : AppCompatActivity(){
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_input)
        context = this
        this.supportActionBar?.hide()
        pickedDate()
        pickedTime()

        val id = intent.getStringExtra("id")
        Log.e("mess", id)
    }

    private fun pickedDate(){
        edt_date.setOnClickListener { showCalendar(this,edt_date) }
        edt_date.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                showCalendar(this,edt_date)
            }
        }
    }

    private fun pickedTime(){
        edt_time.setOnClickListener { showTimePicker(this,edt_time) }
        edt_time.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus){
                showTimePicker(this,edt_time)
            }
        }
    }

    private fun data(){
        val name = edt_username.text.toString()
        val date = edt_date.text.toString()
        val time = edt_time.text.toString()
        val type = edt_type.text.toString()
        val request = edt_request.text.toString()
        bookingData("1", "1", name, date, time, type,request)
    }

    private fun bookingData(
            user_id : String,
            service_id : String,
            name : String,
            date : String,
            time : String,
            type : String,
            request : String
    ){
        val obj = JSONObject()
        obj.put("id_user", user_id)
        obj.put("id_tambal_ban", service_id)
        obj.put("namauser", name)
        obj.put("tanggal_booking", date)
        obj.put("jam_booking", time)
        obj.put("jenis_kendaraan", type)
        obj.put("request", request)
        bookService(obj)
    }

    private fun bookService(obj : JSONObject){

        val que = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(Request.Method.POST, Endpoints.bookService, obj,{
           response ->
           try {
               Log.e("mess", response.toString())
           } catch (e : JSONException){
               Log.e("ERROR", e.toString())
           }
        }, {
            error ->
            Log.e("ERROR", error.toString())
        })
        que.add(req)

    }

}