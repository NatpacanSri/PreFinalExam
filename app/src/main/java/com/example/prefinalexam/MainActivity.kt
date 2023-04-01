package com.example.prefinalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prefinalexam.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var orderList = arrayListOf<Data>()
    var createClient = API.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      binding.recyclerView.adapter = OrderAdapter(this.orderList,applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL)
        )

    }

    override fun onResume() {
        super.onResume()
        callData()
    }

    fun callData(){
        createClient.getData().enqueue(object:Callback<List<Data>>{
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                response.body()?.forEach{
                    orderList.add(Data(it.id,it.username,it.iphone,it.amount,it.storage,it.warranty,it.price))
                }
                binding.recyclerView.adapter = OrderAdapter(orderList,applicationContext)
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure: " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun goAddData(v: View){
        startActivity(Intent(applicationContext,InsertActivity::class.java))
        finish()
    }

}