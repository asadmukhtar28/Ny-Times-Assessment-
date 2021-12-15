package com.asad.nytimes.utils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asad.nytimes.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingUtils {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    fun setImageUrl(
        imageView: ImageView,
        imageUrl: String,
        placeHolder: Int = R.drawable.ic_launcher_background
    ) {
        if (!TextUtils.isEmpty(imageUrl)) {
            val requestOptions = RequestOptions().placeholder(placeHolder).error(placeHolder)
            Glide.with(imageView.context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView)
        }
    }
}