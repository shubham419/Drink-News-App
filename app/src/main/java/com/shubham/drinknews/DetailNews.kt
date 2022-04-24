package com.shubham.drinknews

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class DetailNews : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        val url:String? = intent.getStringExtra("URL")
        val webView = findViewById<WebView>(R.id.webView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        if(url != null){
            webView.settings.javaScriptEnabled = true
            webView.settings.userAgentString = "user_agent','Mozilla/5.0 (Windows; U; Windows NT 6.0; en-GB; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3"
            webView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                }
            }
            webView.loadUrl(url)
        }
    }
}

