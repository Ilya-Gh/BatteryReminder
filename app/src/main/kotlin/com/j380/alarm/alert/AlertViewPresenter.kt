package com.j380.alarm.alert;

interface AlertViewPresenter {

    fun initView()

    fun hideAlert()

    fun showLowBatteryAlert(batteryLevel: Int)
}
