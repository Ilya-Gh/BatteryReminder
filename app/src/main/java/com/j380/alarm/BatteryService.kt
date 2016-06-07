package com.j380.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.j380.alarm.interactor.BatteryInteractorImpl
import com.j380.alarm.presenter.AlertViewPresenter
import com.j380.alarm.presenter.AlertViewPresenterImpl

class BatteryService : Service() {

    private val LOW_BATTERY_LEVEL = 25f

    private lateinit var alertView: AlertViewPresenter

    private lateinit var batteryStatus: Intent

    private var batteryInteractor = BatteryInteractorImpl()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        setAlarm()
        alertView = AlertViewPresenterImpl(this)
        alertView.initView()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        checkBattery()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        alertView.hideAlert()
    }

    private fun setAlarm() {
        val alarmManager = applicationContext.getSystemService(
                Context.ALARM_SERVICE) as AlarmManager;
        val lIntent = Intent(getString(R.string.checkBatteryIntentAction));
        val lPendingIntent = PendingIntent.getBroadcast(applicationContext, 0, lIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, lPendingIntent);
    }

    private fun checkBattery() {
        batteryStatus = getBatteryStatusIntent()
        batteryInteractor.setBatteryStatus(batteryStatus)

        if (batteryInteractor.isNotPluggedIn()) {
            showViewIfBatteryLevelIsLow(batteryInteractor.getBatteryLevel())
        }
    }

    private fun getBatteryStatusIntent(): Intent {
        val lFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        return applicationContext.registerReceiver(null, lFilter)
    }

    private fun showViewIfBatteryLevelIsLow(batteryLevel: Int) {
        if (batteryLevel <= LOW_BATTERY_LEVEL) {
            alertView.showLowBatteryAlert(batteryLevel);
        }
    }
}