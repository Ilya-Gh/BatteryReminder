package com.j380.alarm.injection.component

import android.content.Context
import com.j380.alarm.BatteryService
import com.j380.alarm.injection.module.AppModule
import com.j380.alarm.interactor.BatteryInteractor
import com.j380.alarm.view.PermissionActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(service: BatteryService)

    fun inject(activity: PermissionActivity)


    fun context(): Context

    fun batteryInteractor(): BatteryInteractor
}