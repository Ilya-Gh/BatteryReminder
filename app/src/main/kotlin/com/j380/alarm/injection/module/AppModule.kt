package com.j380.alarm.injection.module

import android.app.Application
import android.content.Context
import com.j380.alarm.interactor.BatteryInteractor
import com.j380.alarm.interactor.BatteryInteractorImpl
import com.j380.alarm.interactor.IntentInteractor
import com.j380.alarm.interactor.IntentInteractorImpl
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

    @Provides
    @Singleton
    fun provideIntentInteractor(context: Context): IntentInteractor = IntentInteractorImpl(context)

}