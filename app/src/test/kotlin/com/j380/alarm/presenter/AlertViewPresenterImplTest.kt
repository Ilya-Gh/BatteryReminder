package com.j380.alarm.presenter

import android.content.Context
import android.media.AudioManager
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.j380.alarm.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlertViewPresenterImplTest {

    val context = mock(Context::class.java)
    val windowManager = mock(WindowManager::class.java)
    val audioManager = mock(AudioManager::class.java)
    val inflater = mock(LayoutInflater::class.java)

    val button = mock(Button::class.java)
    val view = mock(View::class.java)
    val textView = mock(TextView::class.java)

    lateinit var presenter: AlertViewPresenter

    @Before fun setUp() {

        `when`(context.getString(eq(R.string.remain))).thenReturn("OK")
        `when`(inflater.inflate(eq(R.layout.layout_alert_notification), any())).thenReturn(view)
        `when`(view.findViewById(eq(R.id.okBtn))).thenReturn(button)
        `when`(view.findViewById(eq(R.id.remainingTv))).thenReturn(textView)
        presenter = AlertViewPresenterImpl(context, audioManager, windowManager, inflater)
    }

    @Test fun hideAlert_removeViewFromWindowManager() {
        presenter.hideAlert()

        verify(windowManager).removeViewImmediate(eq(view))
    }
}