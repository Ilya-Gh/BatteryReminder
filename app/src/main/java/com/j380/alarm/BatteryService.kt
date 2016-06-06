package com.j380.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.IBinder
import com.j380.alarm.view.AlertView
import com.j380.alarm.view.AlertViewImpl

class BatteryService : Service() {

    private val LOW_BATTERY_LEVEL = 25f

    private lateinit var alertView: AlertView

    private lateinit var batteryStatus: Intent

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        setAlarm()
        alertView = AlertViewImpl(this)
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

        if (isNotPluggedIn()) {
            val batteryLevel = convertBatteryLevelToPercent(getRawBatteryLevel(), getBatteryScale())
            showViewIfBatteryLevelIsLow(batteryLevel)
        }
    }

    private fun getBatteryStatusIntent(): Intent {
        val lFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        return applicationContext.registerReceiver(null, lFilter)
    }

    private fun getRawBatteryLevel() = batteryStatus.getIntExtra(
            BatteryManager.EXTRA_LEVEL, -1)

    private fun getBatteryScale() = batteryStatus.getIntExtra(
            BatteryManager.EXTRA_SCALE, -1)

    private fun convertBatteryLevelToPercent(rawLevel: Int, scale: Int): Int {
        return rawLevel / scale * 100;
    }

    private fun getTypeOfChargePlug() = batteryStatus.getIntExtra(
            BatteryManager.EXTRA_PLUGGED, -1)

    private fun isNotPluggedIn() = !isPluggedInUsbCharger() && !isPluggedInAcCharger()

    private fun isPluggedInUsbCharger(): Boolean {
        return getTypeOfChargePlug() == BatteryManager.BATTERY_PLUGGED_USB
    }

    private fun isPluggedInAcCharger(): Boolean {
        return getTypeOfChargePlug() == BatteryManager.BATTERY_PLUGGED_AC
    }

    private fun showViewIfBatteryLevelIsLow(batteryLevel: Int) {
        if (batteryLevel <= LOW_BATTERY_LEVEL) {
            alertView.showLowBatteryAlert(batteryLevel);
        }
    }
}