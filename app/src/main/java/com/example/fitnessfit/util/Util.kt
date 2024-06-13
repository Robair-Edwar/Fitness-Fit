package com.example.fitnessfit.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import java.util.Locale

object Util {
    fun makeToast(context: Context, data: Int) {
        val text = context.getString(data)
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun makeToast(context: Context, data: String) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    fun getCurrentLanguage(): String {
        return Locale.getDefault().language
    }

}