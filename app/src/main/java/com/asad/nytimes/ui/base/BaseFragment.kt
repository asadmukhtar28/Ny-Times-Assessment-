package com.asad.nytimes.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

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

}