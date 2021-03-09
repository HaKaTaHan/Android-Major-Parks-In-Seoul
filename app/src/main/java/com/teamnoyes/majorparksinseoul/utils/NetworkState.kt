package com.teamnoyes.majorparksinseoul.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.Toast
import com.teamnoyes.majorparksinseoul.R

object NetworkState {
    var state = false
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            state = true
        }

        override fun onLost(network: Network) {
            state = false
            Toast.makeText(context, context.getString(R.string.check_network), Toast.LENGTH_SHORT).show()
        }
    }
    private lateinit var context: Context
    private lateinit var connectivityManager: ConnectivityManager
    private val networkRequest = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()

    fun initNetworkState(context: Context){
        this.context = context
        connectivityManager = context.getSystemService(ConnectivityManager::class.java)
        if (connectivityManager.activeNetwork != null){
            state = true
        }
    }

    fun registerNetworkCallback(){
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun unRegisterNetworkCallback(){
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}