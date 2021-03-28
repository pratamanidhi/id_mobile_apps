package com.tugas_akhir.tambal_ban.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tugas_akhir.tambal_ban.Activity.Activity.DetailActivity
import com.tugas_akhir.tambal_ban.Model.TambalBanModel
import com.tugas_akhir.tambal_ban.R
import com.tugas_akhir.tambal_ban.Util.Collection
import kotlinx.android.synthetic.main.list_tambal_ban.view.*
import java.util.ArrayList

class TambalBanAdapter (val context: Context, val datas : ArrayList<TambalBanModel>) : RecyclerView.Adapter<TambalBanAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TambalBanAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_tambal_ban, parent, false))
    }

    override fun onBindViewHolder(holder: TambalBanAdapter.ViewHolder, position: Int) {
        val data = datas[position]
        holder.title.text = data.nama_tambal_ban
        holder.address.text = data.address
        val price = data.price
        if (price != null) {
            holder.price.text = Collection.IDRFormat(price.toDouble())
        }
        holder.operation.text = data.operation
        holder.warp.setOnClickListener {
            val id = data.id
            val lat = data.latitude
            val lng = data.longitude
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("latitude", lat)
            intent.putExtra("longitude", lng)
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val warp = view.warp
        val title = view.tv_tiket_title
        val address = view.edt_address
        val price = view.edt_price
        val operation = view.edt_operation
    }

}