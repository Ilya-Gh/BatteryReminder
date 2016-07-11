package com.j380.alarm.battery

import android.app.AlarmManager
import android.content.Context
import com.j380.alarm.BuildConfig
import com.j380.alarm.alert.AlertViewPresenter
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(sdk = intArrayOf(21), constants = BuildConfig::class)
class BatteryServicePresenterImplTest {

    lateinit var context: Context
    val view: AlertViewPresenter = mock()
    val batteryInteractor: BatteryInteractor = mock()
    val alarmManager: AlarmManager = mock()

    lateinit var presenter: BatteryServicePresenter

    @Before fun setUpContext() {
        context = mock()
        `when`(context.registerReceiver(any(), any())).thenReturn(mock())
        presenter = BatteryServicePresenterImpl(context, view, batteryInteractor, alarmManager)
    }

    @Test fun onCreate_setAlarmAndInitView() {
        context = RuntimeEnvironment.application as Context
        presenter = BatteryServicePresenterImpl(context, view, batteryInteractor, alarmManager)
        presenter.onCreate()

        verify(alarmManager).setRepeating(eq(AlarmManager.RTC_WAKEUP), any(), eq(AlarmManager
                .INTERVAL_FIFTEEN_MINUTES), any())
        verify(view).initView()
    }

    @Test fun onDestroy_hideAlert() {
        presenter.onDestroy()

        verify(view).hideAlert()
    }

    @Test fun onStartCommand_batteryLow_isNotPluggedIn_showLowBatteryLevelAlert() {
        val BATTERY_LEVEL = 10
        `when`(batteryInteractor.isNotPluggedIn()).thenReturn(true)
        `when`(batteryInteractor.getBatteryLevel()).thenReturn(BATTERY_LEVEL)

        presenter.onStartCommand()

        verify(view).showLowBatteryAlert(BATTERY_LEVEL)
    }

    @Test fun onStartCommand_isPluggedIn_batteryLow_doNotShowLowBatteryLevelAlert() {
        val BATTERY_LEVEL = 10
        `when`(batteryInteractor.isNotPluggedIn()).thenReturn(false)
        `when`(batteryInteractor.getBatteryLevel()).thenReturn(BATTERY_LEVEL)

        presenter.onStartCommand()

        verify(view, never()).showLowBatteryAlert(any())
    }

    @Test fun onStartCommand_batteryIsNotLow_doNotShowLowBatteryLevelAlert() {
        val BATTERY_LEVEL = 36
        `when`(batteryInteractor.isNotPluggedIn()).thenReturn(true)
        `when`(batteryInteractor.getBatteryLevel()).thenReturn(BATTERY_LEVEL)

        presenter.onStartCommand()

        verify(view, never()).showLowBatteryAlert(any())
    }

}