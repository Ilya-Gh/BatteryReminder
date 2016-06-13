package com.j380.alarm

import android.app.Application
import com.j380.alarm.injection.component.AppComponent
import com.j380.alarm.injection.component.DaggerAppComponent
import com.j380.alarm.injection.module.AppModule

class BatteryReminderApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}