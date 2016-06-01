package com.j380.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.IBinder
import com.j380.alarm.receivers.AlertBroadcastReceiver
import com.j380.alarm.view.AlertView

class BatteryService : Service() {

    private var alertBroadcastReceiver = AlertBroadcastReceiver()

    private var batteryPercents: Float = 0f

    private val LOW_BATTERY_LEVEL = 25f

    private lateinit var alertView : AlertView

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        setAlarm()
        alertView = AlertView(this)
        alertView.initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(alertBroadcastReceiver)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        checkBattery()
        return START_STICKY
    }

    fun setAlarm() {
        val alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        val lIntent = Intent(AlertBroadcastReceiver.);
        val lPendingIntent = PendingIntent.getBroadcast(applicationContext, 0, lIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, lPendingIntent);
    }

    fun checkBattery() {
        val lFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = applicationContext.registerReceiver(null, lFilter)

        val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

        if (level != -1 && scale != -1) {
            batteryPercents = level / scale.toFloat();
            batteryPercents *= 100;
        }

        val chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

        if (batteryPercents <= LOW_BATTERY_LEVEL && !usbCharge && !acCharge) {
            alertView.show(batteryPercents);
        }

    }






}