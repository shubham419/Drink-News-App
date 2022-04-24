package com.shubham.drinknews

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.shubham.drinknews.LayoutManager.ColorPicker
import com.shubham.drinknews.LayoutManager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var page = 1
    var totalResult = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.parseColor("#FF000000"))
        }
        supportActionBar!!.hide()

        adapter = NewsAdapter(this@MainActivity, articles)
        val newsList = findViewById<RecyclerView>(R.id.newsList)
        val container = findViewById<ConstraintLayout>(R.id.container)
        newsList.adapter = adapter
        val manager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        manager.setPagerMode(true)
        manager.setPagerFlingVelocity(3000)
        newsList.layoutManager = manager
        manager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                container.setBackgroundColor(Color.parseColor(ColorPicker.getcolor()))

                if (totalResult > manager.itemCount && manager.getFirstVisibleItemPosition() >= manager.itemCount - 5) {
                    page++
                    getNews()
                }
            }
        })
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInterface.getHeadLine("in", page)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val new = response.body()
                if (new != null) {
                    totalResult = new.totalResults
                    articles.addAll(new.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("xyz", "Error in fetching news")
            }
        })
    }

}