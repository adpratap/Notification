package com.noreplypratap.notification.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

object UtilFunctions {

    private const val APP_TAG = "NotificationAppTag"

    fun logMessage(tag: String = APP_TAG, message: String, logLevel: Int = Log.DEBUG) {
        when (logLevel) {
            Log.VERBOSE -> Log.v(tag, message)
            Log.DEBUG -> Log.d(tag, message)
            Log.INFO -> Log.i(tag, message)
            Log.WARN -> Log.w(tag, message)
            Log.ERROR -> Log.e(tag, message)
            else -> Log.d(tag, message)
        }
    }

    fun Activity.onClick(viewId: Int, onClickAction: () -> Unit) {
        findViewById<Button>(viewId).setOnClickListener {
            onClickAction()
        }
    }

    fun Context.showToastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun Context.isOnline() : Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                logMessage("InternetStatus", "Online", Log.INFO)
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                logMessage("InternetStatus", "Online", Log.INFO)
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                logMessage("InternetStatus", "Online", Log.INFO)
                return true
            }
        }
        logMessage("InternetStatus", "No Internet", Log.INFO)
        this.showToastMessage("No Internet")
        return false
    }
}