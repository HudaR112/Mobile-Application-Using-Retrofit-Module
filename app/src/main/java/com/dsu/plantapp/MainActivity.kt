package com.dsu.plantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsu.plantapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var data = ArrayList<Products>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
         getAllProducts()
    }

    private fun getAllProducts() {
       val retrofit = ServiceBuilder.buildService(ServiceInterface::class.java)

        retrofit.getAllProducts().enqueue(object: retrofit2.Callback<ApiRes> {
            override fun onResponse(call: Call<ApiRes>, response: Response<ApiRes>) {
                try{
                    val responseBody=response.body()!!
                    data= responseBody.products

                    var adapter = ProdAdapter(data)
                    binding.recyclerview.adapter=adapter

                }
                catch (ex: java.lang.Exception)
                {
                    ex.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ApiRes>, t: Throwable) {
               Log.e("Failed", "Api Didn't Respond back"+t.message)
            }
        })
    }
}