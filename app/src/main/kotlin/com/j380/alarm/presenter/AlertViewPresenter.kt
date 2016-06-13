package com.j380.alarm.presenter;

interface AlertViewPresenter {

    fun initView()

    fun hideAlert()

    fun showLowBatteryAlert(batteryLevel: Int)
}
