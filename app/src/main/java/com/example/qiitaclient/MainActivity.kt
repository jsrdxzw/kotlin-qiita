package com.example.qiitaclient

import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleClient: ArticleClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)
        setContentView(R.layout.activity_main)
        val articleListAdapter = ArticleListAdapter(applicationContext)
        val listView = findViewById<ListView>(R.id.list_view)
        listView.adapter = articleListAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val article = articleListAdapter.articles[position]
            startActivity(ArticleActivity.intent(this, article))
        }

        search_button.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            articleClient.search(query_edit_text.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    progress_bar.visibility = View.GONE
                }
                .compose(bindToLifecycle())
                .subscribe({
                    query_edit_text.text.clear()
                    articleListAdapter.articles = it
                    articleListAdapter.notifyDataSetChanged()

                }, {
                    toast("エラー$it")
                })
        }
    }

}

