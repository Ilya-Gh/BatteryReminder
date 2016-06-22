package com.j380.alarm.service

import android.os.IBinder

interface BatteryServicePresenter {

    fun onBind(): IBinder?

    fun onCreate()

    fun onStartCommand()

    fun onDestroy()
}