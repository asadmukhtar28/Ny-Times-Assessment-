package com.asad.nytimes.ui.base

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.asad.nytimes.R
import com.asad.nytimes.models.base.State

abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {
    protected abstract val viewModel: VM
    protected lateinit var viewBinding: VB
    protected var baseActivity: BaseActivity<*, *>? = null

    protected abstract fun getBindingVariable(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity<*, *>)
            baseActivity = context
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    open fun initUi() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.setVariable(getBindingVariable(), viewModel)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    fun setAppropriateErrorMessage(responseError: State.ResponseError): String {
        return when (responseError) {
            is State.ResponseError.SomethingWentWrong -> {
                if (!TextUtils.isEmpty(responseError.message))
                    responseError.message
                else getString(R.string.something_went_wrong)
            }
            is State.ResponseError.InternetConnectionResponseError -> {
                getString(R.string.internet_error)
            }
        }
    }
}