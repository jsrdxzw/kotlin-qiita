package com.example.qiitaclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView
import com.example.qiitaclient.model.Article
import com.example.qiitaclient.view.ArticleView


class ArticleActivity : AppCompatActivity() {

    companion object{
        private const val ARTICLE_EXTRA = "article"
        fun intent(context: Context,article:Article):Intent =
                Intent(context,ArticleActivity::class.java).putExtra(ARTICLE_EXTRA,article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val articleView = findViewById<ArticleView>(R.id.article_view)
        val webView = findViewById<WebView>(R.id.web_view)
        val article = intent.getParcelableExtra<Article>(ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }

}
