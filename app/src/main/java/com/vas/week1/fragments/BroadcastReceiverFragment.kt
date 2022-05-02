package com.vas.week1.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.vas.week1.receivers.NetworkChangeReceiver
import com.vas.week1.R
import com.vas.week1.databinding.FragmentBroadcastReceiverBinding

/**
 * Код BroadcastReceiverFragment.
 * Данный фрагмент ознакамливает нас с функционалом BroadcastReceiver.
 * BroadcastReceiver часто используется в android приложениях
 * (к примеру YouTube смотрит состояние сети и сообщает пользователю о прерванном соединении)
 * и играх (подключение к зарядке, включен ли блютуз и wi-fi)
 */

class BroadcastReceiverFragment : Fragment() {

    private lateinit var binding: FragmentBroadcastReceiverBinding
    private var myNetworkReceiver: BroadcastReceiver? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBroadcastReceiverBinding.inflate(inflater, container, false)

        /*
        Создаем объект NetworkChangeReceiver (принимает сообщения о состоянии сети).
        В конструктор передаем ему реализацию метода интерфейса для работы с полученной информацией.
         */
        myNetworkReceiver = NetworkChangeReceiver(object : NetworkChangeReceiver.ReceiverListener{
            override fun onNetworkChange(isConnected: Boolean) {
                Snackbar.make(
                    binding.imageViewNetwork,
                    if (isConnected) "Соединение установлено" else "Соединение отсутствует",
                    Snackbar.LENGTH_INDEFINITE).setAction("Ок"
                ) {
                    binding.imageViewNetwork.setImageResource(
                        if (isConnected) R.drawable.ic_network
                        else R.drawable.ic_no_network)
                }.show()
            }
        })

        initTextView()
        registerForNetworkBroadcast()

        return binding.root
    }

    // регистрация приемника
    private fun registerForNetworkBroadcast() {
        activity?.registerReceiver(
            myNetworkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    // снятие приемника
    private fun unregisterNetworkChanges() {
        activity?.unregisterReceiver(myNetworkReceiver)
    }

    /*
    Берем данные из SharedPreferences о времени последнего перезапуска устройства
    и выводим на экран.
     */
    private fun initTextView() {
        val sharedPref: SharedPreferences? = context?.getSharedPreferences(
            "restart_pref", Context.MODE_PRIVATE)

        binding.broadcastTextView.text = sharedPref?.getString(
            "last_restart", null) ?: "Устройство не было перезапущено"
    }

    //снимаем регистрацию с приемника, когда закончена работа с фрагментом
    override fun onDestroy() {
        unregisterNetworkChanges()
        super.onDestroy()
    }

}