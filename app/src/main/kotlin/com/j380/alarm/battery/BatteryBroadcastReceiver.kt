package com.j380.alarm.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.j380.alarm.battery.BatteryService

class BatteryBroadcastReceiver : BroadcastReceiver () {

    override fun onReceive(context: Context, intent: Intent?) {
        val startServiceIntent = Intent(context, BatteryService::class.java)
        context.startService(startServiceIntent)
    }
}