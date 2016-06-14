package com.j380.alarm.injection.component

import com.j380.alarm.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun plusPermissionComponent(): PermissionActivityComponent

    fun plusBatteryComponent(): BatteryServiceComponent
}