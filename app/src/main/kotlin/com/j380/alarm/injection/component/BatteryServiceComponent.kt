package com.j380.alarm.injection.component

import com.j380.alarm.BatteryService
import com.j380.alarm.injection.annotation.PerService
import com.j380.alarm.injection.module.BatteryServiceModule
import com.j380.alarm.presenter.AlertViewPresenter
import dagger.Subcomponent

@PerService
@Subcomponent(modules = arrayOf(BatteryServiceModule::class))
interface BatteryServiceComponent {

    fun inject(service: BatteryService)

    fun alertViewPresenter(): AlertViewPresenter
}