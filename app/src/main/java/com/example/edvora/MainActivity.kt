package com.example.edvora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.edvora.Adaptors.DataAdapter
import com.example.edvora.Adaptors.ViewPageAdapter
import com.example.edvora.Api.UserApiInterface
import com.example.edvora.ModelClasses.DataModel
import com.example.edvora.ModelClasses.User
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val BASE_URL = "https://assessment.api.vweb.app/"
class MainActivity : AppCompatActivity() {
    lateinit var circleImageView: CircleImageView
    lateinit var username: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        circleImageView = findViewById(R.id.profileImage)
        username = findViewById(R.id.userName)

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(UserApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : retrofit2.Callback<User>{
            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                println(t.message.toString() + "He")

            }

            override fun onResponse(call: retrofit2.Call<User>, response: retrofit2.Response<User>) {
                println("Success")
                val responseBody = response.body()!!

                val URL = responseBody.url
                username.text = responseBody.name
                Picasso.get().load(URL).centerCrop().resize(200,200).into(circleImageView)

            }
        })
        // Setting up tab layout

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.view_Pager)

        val adapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> {
                    tab.text = "Nearest"
                } 1 -> {
                    tab.text = "Upcoming"
                } 2 -> {
                    tab.text = "Past"
                }
            }
        }.attach()


    }
}