package com.j380.alarm.permissions

import android.app.Activity
import android.content.Intent
import com.j380.alarm.intent.IntentInteractor
import com.j380.alarm.permissions.PermissionInteractor
import com.j380.alarm.permissions.PermissionPresenterImpl
import com.j380.alarm.permissions.PermissionView
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PermissionPresenterImplTest {

    lateinit var presenter: PermissionPresenterImpl

    var intentInteractor: IntentInteractor = mock()
    var permissionInteractor: PermissionInteractor = mock()

    var view: PermissionView = mock()

    @Before fun setUp() {
        presenter = PermissionPresenterImpl(intentInteractor, permissionInteractor)
        presenter.setView(view)
    }

    @Test fun onCreate_startActivityForResult() {
        val intent: Intent = mock()
        `when`(intentInteractor.getRequestPermissionIntent()).thenReturn(intent)
        `when`(permissionInteractor.isDrawOverlayPermissionGranted()).thenReturn(false)

        presenter.onCreate()

        verify(view).startActivityForResult(eq(intent), eq((presenter).REQUEST_CODE))
    }

    @Test fun onCreate_startServiceAndCloseView() {
        val intent: Intent = mock()
        `when`(intentInteractor.getStartBatteryServiceIntent()).thenReturn(intent)
        `when`(permissionInteractor.isDrawOverlayPermissionGranted()).thenReturn(true)

        presenter.onCreate()

        verify(view).startService(eq(intent))
        verify(view).closeActivity()
    }

    @Test fun onActivityResult_startServiceAndCloseView() {
        val intent: Intent = mock()
        `when`(intentInteractor.getStartBatteryServiceIntent()).thenReturn(intent)
        `when`(permissionInteractor.isDrawOverlayPermissionGranted()).thenReturn(true)

        presenter.onActivityResult(1, Activity.RESULT_OK, intent)

        verify(view).startService(eq(intent))
        verify(view).closeActivity()
    }

}