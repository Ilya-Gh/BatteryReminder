package com.j380.alarm.battery

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.j380.alarm.BatteryReminderApplication
import javax.inject.Inject

class BatteryService : Service() {

    @Inject lateinit var batteryServicePresenter: BatteryServicePresenter

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        BatteryReminderApplication.appComponent.plusBatteryComponent().inject(this)
        batteryServicePresenter.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        batteryServicePresenter.onStartCommand()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        batteryServicePresenter.onDestroy()
    }
}