package com.j380.alarm.injection.module

import android.app.Application
import android.app.Service
import android.content.Context
import android.media.AudioManager
import android.view.LayoutInflater
import android.view.WindowManager
import com.j380.alarm.interactor.*
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

    @Provides
    @Singleton
    fun providePermissionInteractor(context: Context): PermissionInteractor =
            PermissionInteractorImpl(context)

    @Provides
    @Singleton
    fun provideAudioManager(context: Context) = context.getSystemService(Service.AUDIO_SERVICE)
            as AudioManager

    @Provides
    @Singleton
    fun provideWindowManager(context: Context) = context.getSystemService(Service.WINDOW_SERVICE)
            as WindowManager

    @Provides
    @Singleton
    fun provideLayoutInflater(context: Context) = context.getSystemService(Service
            .LAYOUT_INFLATER_SERVICE) as LayoutInflater

}