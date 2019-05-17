package com.example.qiitaclient.view

import android.content.Context
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.qiitaclient.R
import com.example.qiitaclient.databinding.ViewArticleBinding
import com.example.qiitaclient.model.Article

@BindingMethods(BindingMethod(type = Article::class,attribute = "bind:article",method = "setArticle"))
class ArticleView : FrameLayout {

    var binding: ViewArticleBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_article, this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setArticle(article: Article) {
        binding.article = article
    }
}