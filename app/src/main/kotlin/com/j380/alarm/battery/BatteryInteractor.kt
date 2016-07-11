package com.j380.alarm.battery

import android.content.Intent

interface BatteryInteractor {

    fun setBatteryStatus(batteryIntent: Intent)

    fun isNotPluggedIn(): Boolean

    fun getBatteryLevel(): Int
}