package com.j380.alarm.injection.module

import android.content.Context
import android.graphics.PixelFormat
import android.media.AudioManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
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
            windowManager: WindowManager, inflater: LayoutInflater, params: LayoutParams):
            AlertViewPresenter {
        return AlertViewPresenterImpl(context, audioManager, windowManager, inflater, params)
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