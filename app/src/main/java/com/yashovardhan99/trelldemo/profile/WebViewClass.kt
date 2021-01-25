package com.yashovardhan99.trelldemo.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.yashovardhan99.trelldemo.R

class WebViewClass : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_class)

        webView = findViewById(R.id.webview)
        webView.settings.setJavaScriptEnabled(true)

        /*webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }*/
        webView.loadUrl("https://www.google.co.in/")
    }
}