package com.j380.alarm.view;

interface AlertView {

    fun initView()

    fun hideAlert()

    fun showLowBatteryAlert(batteryLevel: Int)
}
