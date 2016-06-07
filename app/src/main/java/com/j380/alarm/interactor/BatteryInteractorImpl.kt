package com.j380.alarm.interactor

import android.content.Intent
import android.os.BatteryManager

class BatteryInteractorImpl : BatteryInteractor {

    private lateinit var batteryStatus: Intent

    override fun setBatteryStatus(batteryIntent: Intent) {
        this.batteryStatus = batteryIntent
    }

    override fun isNotPluggedIn() = !isPluggedInUsbCharger() && !isPluggedInAcCharger()

    override fun getBatteryLevel() = calculateBatteryLevelIntPercent(getRawBatteryLevel(),
            getBatteryScale())

    private fun getRawBatteryLevel() = batteryStatus.getIntExtra(
            BatteryManager.EXTRA_LEVEL, -1)

    private fun getBatteryScale() = batteryStatus.getIntExtra(
            BatteryManager.EXTRA_SCALE, -1)

    private fun calculateBatteryLevelIntPercent(rawLevel: Int, scale: Int): Int {
        return (rawLevel / scale.toFloat() * 100).toInt();
    }

    private fun getTypeOfChargePlug() = batteryStatus.getIntExtra(
            BatteryManager.EXTRA_PLUGGED, -1)

    private fun isPluggedInUsbCharger(): Boolean {
        return getTypeOfChargePlug() == BatteryManager.BATTERY_PLUGGED_USB
    }

    private fun isPluggedInAcCharger(): Boolean {
        return getTypeOfChargePlug() == BatteryManager.BATTERY_PLUGGED_AC
    }
}