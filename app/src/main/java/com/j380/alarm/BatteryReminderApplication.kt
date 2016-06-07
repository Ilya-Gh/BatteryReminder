package com.j380.alarm

import android.app.Application
import com.j380.alarm.injection.AppComponent
import com.j380.alarm.injection.AppModule
import com.j380.alarm.injection.DaggerAppComponent

class BatteryReminderApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}