package com.j380.alarm.interactor

import android.content.Context
import android.net.Uri
import com.j380.alarm.BatteryService
import com.j380.alarm.BuildConfig
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(sdk = intArrayOf(21), constants = BuildConfig::class)
class IntentInteractorImplTest {

    lateinit var context: Context
    lateinit var interactor: IntentInteractorImpl

    @Before fun setUp() {
        context = RuntimeEnvironment.application as Context
        interactor = IntentInteractorImpl(context)
    }

    @Test fun getStartBatteryServiceIntent() {
        val intent = interactor.getStartBatteryServiceIntent()
        val expectedClassName = BatteryService::class.java

        assertEquals(expectedClassName.canonicalName, intent.component.className)
    }

    @Test fun getRequestPermissionIntent() {
        val intent = interactor.getRequestPermissionIntent()
        val expectedUri = Uri.parse("package:com.j380.alarm")
        val expectedAction = "android.settings.action.MANAGE_OVERLAY_PERMISSION"

        assertEquals(expectedUri, intent.data)
        assertEquals(expectedAction, intent.action)
    }

}