package com.vas.week1.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vas.week1.services.RestartService

class RestartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // запускаем сервис для записи данных о перезапуске устройства
        context?.startService(Intent(context, RestartService::class.java))
    }

}