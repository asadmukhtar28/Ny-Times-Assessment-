package com.asad.nytimes.ui.articles

import android.text.TextUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.asad.nytimes.BR
import com.asad.nytimes.R
import com.asad.nytimes.databinding.FragmentArticlesBinding
import com.asad.nytimes.models.base.State
import com.asad.nytimes.ui.articles.adapter.ArticleAdapter
import com.asad.nytimes.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesFragment :
    BaseFragment<ArticlesViewModel, FragmentArticlesBinding>(R.layout.fragment_articles) {

    @Inject
    lateinit var adapter: ArticleAdapter

    override val viewModel: ArticlesViewModel by viewModels()
    override fun getBindingVariable() = BR.viewModel

    override fun initUi() {
        viewBinding.rvArticles.adapter = adapter
        adapter.setOnItemClickListener {
            findNavController().navigate(
                ArticlesFragmentDirections.actionNavArticlesFragmentToArticleDetailFragment(
                    it
                )
            )
        }

        setUpObserver()
        viewModel.fetchArticles()
    }

    private fun setUpObserver() {
        viewModel.error.observe(viewLifecycleOwner, {
            val errorMessage = when (it) {
                is State.ResponseError.SomethingWentWrong -> {
                    if (!TextUtils.isEmpty(it.message))
                        it.message
                    else getString(R.string.something_went_wrong)
                }
                is State.ResponseError.InternetConnectionResponseError -> {
                    getString(R.string.internet_error)
                }
            }

            Snackbar.make(viewBinding.root, errorMessage, Snackbar.LENGTH_LONG)
                .setAction("Ok") {}
                .show()
        })
    }
}