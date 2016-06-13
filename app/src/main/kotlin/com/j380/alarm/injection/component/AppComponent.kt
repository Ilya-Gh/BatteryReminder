package com.j380.alarm.injection.component

import android.content.Context
import android.media.AudioManager
import android.view.LayoutInflater
import android.view.WindowManager
import com.j380.alarm.injection.module.AppModule
import com.j380.alarm.interactor.BatteryInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun plusPermissionComponent(): PermissionActivityComponent

    fun plusBatteryComponent(): BatteryServiceComponent

    fun context(): Context

    fun batteryInteractor(): BatteryInteractor

    fun audioManager(): AudioManager

    fun windowManager(): WindowManager

    fun layoutInflater(): LayoutInflater
}