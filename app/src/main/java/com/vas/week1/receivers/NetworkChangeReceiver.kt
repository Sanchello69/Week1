package com.vas.week1.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

class NetworkChangeReceiver(var listener: ReceiverListener? = null) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        if (listener != null) {

            val isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting

            Log.d("status_network", isConnected.toString())

            listener!!.onNetworkChange(isConnected)
        }
    }

    interface ReceiverListener {
        fun onNetworkChange(isConnected: Boolean)
    }

}