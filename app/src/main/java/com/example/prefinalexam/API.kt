package com.example.prefinalexam

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @GET("getdata")
    fun getData():Call<List<Data>>

    @FormUrlEncoded
    @POST("adddata")
    fun addData(
        @Field("username") username:String,
        @Field("iphone") iphone:String,
        @Field("amount") amount:Int,
        @Field("storage") storage:String,
        @Field("warranty") warranty:Int,
        @Field("price") price:Int
    ):Call<Data>

    companion object{
        fun create():API{
            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
        }
    }

}