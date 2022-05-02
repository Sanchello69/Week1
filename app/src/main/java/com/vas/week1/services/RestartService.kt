package com.vas.week1.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.text.DateFormat
import java.util.*

class RestartService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val sharedPref: SharedPreferences = this.getSharedPreferences("restart_pref", Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor = sharedPref.edit()

        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        editor.putString("last_restart", "год:$year месяц:$month день:$day $hour:$minute")

        editor.commit()

        stopSelf()

        return super.onStartCommand(intent, flags, startId)
    }
}