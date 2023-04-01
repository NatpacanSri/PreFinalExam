package com.example.prefinalexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.prefinalexam.databinding.ActivityMainBinding
import com.example.prefinalexam.databinding.ItemLayoutBinding

class OrderAdapter(val orderList:List<Data>,val context: Context):
RecyclerView.Adapter<OrderAdapter.ViewHolder>(){
    inner class ViewHolder(view: View,val binding: ItemLayoutBinding):
    RecyclerView.ViewHolder(view){
        init {
            binding.id.setOnClickListener {
                Toast.makeText(context,binding.id.text.toString(),Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding

        binding.id.text = "ID: ${orderList!![position].id}"
        binding.username.text = orderList[position].username
        binding.phone.text = orderList[position].iphone
        binding.amount.text = orderList[position].amount.toString()
        binding.storage.text = orderList[position].storage
        binding.price.text = orderList[position].price.toString()
        binding.warranty.isChecked = orderList[position].warranty == 1

    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}