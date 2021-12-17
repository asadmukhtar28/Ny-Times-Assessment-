package com.asad.nytimes.ui.articledetail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.asad.nytimes.BR
import com.asad.nytimes.R
import com.asad.nytimes.databinding.FragmentArticleDetailBinding
import com.asad.nytimes.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment :
    BaseFragment<ArticleDetailViewModel, FragmentArticleDetailBinding>(R.layout.fragment_article_detail) {
    private val args: ArticleDetailFragmentArgs by navArgs()

    override val viewModel: ArticleDetailViewModel by viewModels()

    override fun getBindingVariable() = BR.viewModel

    override fun initUi() {
        viewModel.setArticleData(args.article)
    }

}