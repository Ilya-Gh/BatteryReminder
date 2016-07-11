package com.j380.alarm.battery

import android.os.IBinder

interface BatteryServicePresenter {

    fun onBind(): IBinder?

    fun onCreate()

    fun onStartCommand()

    fun onDestroy()
}