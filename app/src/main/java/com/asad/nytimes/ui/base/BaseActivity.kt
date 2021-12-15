package com.asad.nytimes.ui.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


/**
 * Abstract Activity which binds [ViewModel] [VM] and [ViewDataBinding] [VB]
 */
abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
) : AppCompatActivity() {
    protected abstract val viewModel: VM
    protected lateinit var viewBinding: VB
    protected lateinit var context: Context

    protected abstract fun getBindingVariable(): Int

    open fun initUi() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        viewBinding = DataBindingUtil.setContentView(this, layoutResId)
        viewBinding.setVariable(getBindingVariable(), viewModel)
        viewBinding.lifecycleOwner = this
        initUi()
    }

}
