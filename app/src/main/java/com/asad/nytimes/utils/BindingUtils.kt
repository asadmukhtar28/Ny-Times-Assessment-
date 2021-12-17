package com.asad.nytimes.utils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asad.nytimes.R
import com.asad.nytimes.models.Article
import com.asad.nytimes.ui.articles.adapter.ArticleAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingUtils {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    fun setImageUrl(
        imageView: ImageView,
        imageUrl: String,
        placeHolder: Int = R.drawable.ic_article
    ) {
        if (!TextUtils.isEmpty(imageUrl)) {
            val requestOptions = RequestOptions().placeholder(placeHolder).error(placeHolder)
            Glide.with(imageView.context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("setArticleAdapter")
    fun setArticleAdapter(recyclerView: RecyclerView, articles: List<Article> ?) {
        if (recyclerView.adapter != null && articles !=null) {
            val adapter = recyclerView.adapter as ArticleAdapter
            adapter.articleList = articles
        }
    }
}