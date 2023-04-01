package com.example.prefinalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import com.example.prefinalexam.databinding.ActivityInsertBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertActivity : AppCompatActivity() {
    private lateinit var bindingInsert:ActivityInsertBinding
    var createClient = API.create()
    var iphone:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInsert = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(bindingInsert.root)

        showDropdown()

    }

    private fun showDropdown(){
        bindingInsert.AutoCompleteTextView.setText("iphone")
        val sub = resources.getStringArray(R.array.iphone)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,sub)
        bindingInsert.AutoCompleteTextView.setAdapter(arrayAdapter)
        bindingInsert.AutoCompleteTextView.setOnItemClickListener{ parent,_,position,_ ->
            iphone = parent.getItemAtPosition(position) as String
        }
    }

    fun submit(v: View){
        var storage:String = ""
        var storagePrice:Int = 0
        var warranty:Int = 0
        var warrantyPrice:Int = 0
        var price:Int = 0

        var radioButtonChecked: RadioButton = findViewById(bindingInsert.radio.checkedRadioButtonId)
        storage = radioButtonChecked.text as String


        when(storage){
            "128GB" -> {
                storagePrice = 0
            }
            "256GB" -> {
                storagePrice = 1200
            }
            "512GB" -> {
                storagePrice = 2400
            }
            "1TB" -> {
                storagePrice = 5600
            }
        }

        if (bindingInsert.warranty.isChecked){
            warranty = 1
            warrantyPrice = 3000
        }

        when(iphone){
            "iphone 12" ->{
                price = (23000+storagePrice+warrantyPrice) * bindingInsert.amount.text.toString().toInt()
            }
            "iphone 13" ->{
                price = (29000+storagePrice+warrantyPrice) * bindingInsert.amount.text.toString().toInt()
            }
            "iphone 14" ->{
                price = (32000+storagePrice+warrantyPrice) * bindingInsert.amount.text.toString().toInt()
            }
        }

        createClient.addData(
            bindingInsert.username.text.toString(),
            iphone,
            bindingInsert.amount.text.toString().toInt(),
            storage,
            warranty,
            price
        ).enqueue(object :Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure: " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

}