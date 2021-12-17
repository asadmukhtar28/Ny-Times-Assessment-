package com.asad.nytimes.ui.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asad.nytimes.databinding.ItemArticleBinding
import com.asad.nytimes.interfaces.OnItemClickListener
import com.asad.nytimes.models.Article
import com.asad.nytimes.ui.base.BaseViewHolder
import javax.inject.Inject

class ArticleAdapter @Inject constructor() :
    RecyclerView.Adapter<ArticleAdapter.ArticleItemViewHolder>() {

    private lateinit var onItemClickListener: ((Article) -> Unit)

    private val diffUtil = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private val listDiffer = AsyncListDiffer(this, diffUtil)

    var articleList: List<Article>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        return ArticleItemViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ArticleItemViewHolder(private val binding: ItemArticleBinding) :
        BaseViewHolder(binding.root), OnItemClickListener {
        override fun onBind(position: Int) {
            binding.viewModel = ArticleItemViewModel(articleList[position], this)
            binding.executePendingBindings()
        }

        override fun onItemClick() {
            onItemClickListener.invoke(articleList[adapterPosition])
        }
    }

}