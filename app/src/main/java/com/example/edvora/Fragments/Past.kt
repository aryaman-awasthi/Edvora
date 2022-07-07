package com.example.edvora.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.Adaptors.DataAdapter
import com.example.edvora.Api.RideApiInterface
import com.example.edvora.ModelClasses.DataModel
import com.example.edvora.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Past : Fragment() {
    lateinit var dataAdapter: DataAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RideApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : retrofit2.Callback<List<DataModel>>{
            override fun onFailure(call: retrofit2.Call<List<DataModel>>, t: Throwable) {
                println("Error")
            }

            override fun onResponse(call: retrofit2.Call<List<DataModel>>, response: retrofit2.Response<List<DataModel>>) {
                println("Success")
                val responseBody = response.body()!!

                recyclerView = view.findViewById(R.id.recycler_view)
                recyclerView.setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = linearLayoutManager
                dataAdapter = DataAdapter(context!!, responseBody)
                dataAdapter.notifyDataSetChanged()
                recyclerView.adapter = dataAdapter
            }
        })
    }

}