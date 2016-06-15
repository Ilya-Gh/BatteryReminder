package com.j380.alarm.interactor

import android.content.Intent
import android.os.BatteryManager
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BatteryInteractorImplTest {

    val interactor = BatteryInteractorImpl()

    @Mock lateinit var batteryStatus: Intent

    @Before fun setUp() {
        interactor.setBatteryStatus(batteryStatus)
    }

    @Test fun isNotPluggedIn_PluggedInUsb() {
        `when`(batteryStatus.getIntExtra(eq(BatteryManager.EXTRA_PLUGGED), anyInt())).thenReturn(
                BatteryManager.BATTERY_PLUGGED_USB)

        val isNotPluggedIn = interactor.isNotPluggedIn()
        assertFalse(isNotPluggedIn)
    }

    @Test fun isNotPluggedIn_PluggedInAc() {
        `when`(batteryStatus.getIntExtra(eq(BatteryManager.EXTRA_PLUGGED), anyInt())).thenReturn(
                BatteryManager.BATTERY_PLUGGED_AC)

        val isNotPluggedIn = interactor.isNotPluggedIn()
        assertFalse(isNotPluggedIn)
    }

    @Test fun isNotPluggedIn_NotPluggedIn() {
        `when`(batteryStatus.getIntExtra(eq(BatteryManager.EXTRA_PLUGGED), anyInt())).thenReturn(
                -1)

        val isNotPluggedIn = interactor.isNotPluggedIn()
        assertTrue(isNotPluggedIn)
    }
}

