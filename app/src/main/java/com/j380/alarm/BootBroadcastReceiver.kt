package com.j380.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootBroadcastReceiver : BroadcastReceiver () {

    override fun onReceive(context: Context, intent: Intent?) {
        val batteryIntent = Intent(context, BatteryService::class.java)
        context.startService(batteryIntent)
    }

}