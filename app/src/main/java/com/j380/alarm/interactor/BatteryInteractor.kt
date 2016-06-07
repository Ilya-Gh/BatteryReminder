package com.j380.alarm.interactor

import android.content.Intent

interface BatteryInteractor {

    fun setBatteryStatus(batteryIntent: Intent)

    fun isNotPluggedIn(): Boolean

    fun getBatteryLevel(): Int
}