package com.example.edvora.Adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.ModelClasses.DataModel
import com.example.edvora.R
import com.squareup.picasso.Picasso

class DataAdapter (val context: Context, val dataList: List<DataModel>) : RecyclerView.Adapter<DataAdapter.ViewHolder>(){
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var cityName: TextView
        var stateName: TextView
        var rideId : TextView
        var o_station : TextView
        var s_path : TextView
        var date : TextView
        var distance : TextView
        var imageView: ImageView

        init {
            cityName = itemView.findViewById(R.id.cityName)
            stateName = itemView.findViewById(R.id.stateName)
            rideId = itemView.findViewById(R.id.rideId)
            o_station = itemView.findViewById(R.id.origin_station)
            s_path = itemView.findViewById(R.id.station_path)
            date = itemView.findViewById(R.id.date)
            distance = itemView.findViewById(R.id.distance)
            imageView = itemView.findViewById(R.id.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = dataList[position].city
        holder.stateName.text = dataList[position].state;
        holder.rideId.text = dataList[position].id.toString()
        holder.o_station.text = dataList[position].origin_station_code.toString()
        holder.s_path.text = dataList[position].station_path.toString()
        holder.date.text = dataList[position].date
        var x = Int.MAX_VALUE
        for (y : Int in dataList[position].station_path){
            var j = kotlin.math.abs(40 - y)
            if (j < x) {
                x = j
            }
        }
        holder.distance.text = x.toString()
        var imageURL = dataList[position].map_url
        Picasso.get().load(imageURL).centerCrop().resize(800,532).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}