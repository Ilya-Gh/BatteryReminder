package com.j380.alarm.injection

import android.app.Application
import android.content.Context
import com.j380.alarm.interactor.BatteryInteractor
import com.j380.alarm.interactor.BatteryInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideBatteryInteractor(): BatteryInteractor = BatteryInteractorImpl()

}