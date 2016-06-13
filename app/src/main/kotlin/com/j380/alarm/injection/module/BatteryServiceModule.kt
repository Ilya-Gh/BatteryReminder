package com.j380.alarm.injection.module

import android.content.Context
import android.media.AudioManager
import android.view.LayoutInflater
import android.view.WindowManager
import com.j380.alarm.injection.annotation.PerService
import com.j380.alarm.presenter.AlertViewPresenter
import com.j380.alarm.presenter.AlertViewPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class BatteryServiceModule {

    @Provides
    @PerService
    fun provideAlertViewPresenter(context: Context, audioManager: AudioManager,
            windowManager: WindowManager, inflater: LayoutInflater): AlertViewPresenter {
        return AlertViewPresenterImpl(context, audioManager, windowManager, inflater)
    }
}