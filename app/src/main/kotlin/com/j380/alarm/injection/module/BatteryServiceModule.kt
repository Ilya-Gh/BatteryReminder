package com.j380.alarm.injection.module

import android.app.AlarmManager
import android.content.Context
import android.graphics.PixelFormat
import android.media.AudioManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import com.j380.alarm.alert.AlertViewPresenter
import com.j380.alarm.alert.AlertViewPresenterImpl
import com.j380.alarm.alert.MediaPlayerFactory
import com.j380.alarm.alert.MediaPlayerFactoryImpl
import com.j380.alarm.battery.BatteryInteractor
import com.j380.alarm.battery.BatteryServicePresenter
import com.j380.alarm.battery.BatteryServicePresenterImpl
import com.j380.alarm.injection.annotation.PerService
import dagger.Module
import dagger.Provides

@Module
class BatteryServiceModule {

    @Provides
    @PerService
    fun provideAlertViewPresenter(context: Context, audioManager: AudioManager,
            windowManager: WindowManager, mediaPlayerFactory: MediaPlayerFactory,
            inflater: LayoutInflater, params: LayoutParams): AlertViewPresenter {
        return AlertViewPresenterImpl(context, audioManager, mediaPlayerFactory,windowManager,
                inflater, params)
    }

    @Provides
    @PerService
    fun provideBatteryServicePresenter(context: Context, alertViewPresenter: AlertViewPresenter,
            batteryInteractor: BatteryInteractor, alarmManager: AlarmManager):
            BatteryServicePresenter {
        return BatteryServicePresenterImpl(context, alertViewPresenter, batteryInteractor,
                alarmManager)
    }

    @Provides
    @PerService
    fun provideMediaPlayerFabric(context: Context): MediaPlayerFactory =
            MediaPlayerFactoryImpl(context)

    @Provides
    @PerService
    fun provideAlarmManager(context: Context): AlarmManager {
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    @Provides
    @PerService
    fun provideLayoutParams(): WindowManager.LayoutParams {
        val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.TYPE_SYSTEM_ALERT,
                LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)
        params.gravity = Gravity.CENTER
        return params
    }
}