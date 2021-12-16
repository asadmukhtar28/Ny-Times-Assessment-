package com.asad.nytimes.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtils {
    fun isNetworkConnected(context: Context?): Boolean {
        var connectivity = false
        if (context != null) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                connectivity =  when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                connectivityManager.run {
                    activeNetworkInfo?.run {
                        connectivity =  when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }

        }
        return connectivity
    }
}