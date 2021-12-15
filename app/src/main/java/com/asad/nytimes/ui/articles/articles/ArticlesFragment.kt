package com.asad.nytimes.ui.articles.articles

import androidx.fragment.app.viewModels
import com.asad.nytimes.BR
import com.asad.nytimes.R
import com.asad.nytimes.databinding.FragmentArticlesBinding
import com.asad.nytimes.ui.base.BaseFragment

class ArticlesFragment :
    BaseFragment<ArticlesViewModel, FragmentArticlesBinding>(R.layout.fragment_articles) {

    override val viewModel: ArticlesViewModel by viewModels()
    override fun getBindingVariable() = BR.viewModel


    override fun initUi() {

    }
}