package com.example.prefinalexam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @Expose
    @SerializedName("id") val id:Int,

    @Expose
    @SerializedName("username") val username:String,

    @Expose
    @SerializedName("iphone") val iphone:String,

    @Expose
    @SerializedName("amount") val amount:Int,

    @Expose
    @SerializedName("storage") val storage:String,

    @Expose
    @SerializedName("warranty") val warranty:Int,

    @Expose
    @SerializedName("price") val price:Int

)
