package com.shubham.drinknews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=b145bc38ee284bf5a190b1367cac8f14

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "b145bc38ee284bf5a190b1367cac8f14"

interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLine(@Query("country") country: String, @Query("page") pageNo: Int): Call<News>

}

object NewsService {

    val newsInterface: NewsInterface

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        newsInterface = retrofit.create(NewsInterface::class.java)
    }

}
