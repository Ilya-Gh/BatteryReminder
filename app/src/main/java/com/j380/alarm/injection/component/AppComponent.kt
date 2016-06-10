package com.j380.alarm.injection.component

import android.content.Context
import com.j380.alarm.BatteryService
import com.j380.alarm.injection.module.AppModule
import com.j380.alarm.interactor.BatteryInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(service: BatteryService)

    fun plus(): PermissionActivityComponent

    fun context(): Context

    fun batteryInteractor(): BatteryInteractor
}