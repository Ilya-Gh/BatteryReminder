package com.j380.alarm.alert

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnSeekCompleteListener
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.j380.alarm.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.invocation.InvocationOnMock
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AlertViewPresenterImplTest {

    @Mock lateinit var context: Context
    @Mock lateinit var windowManager: WindowManager
    @Mock lateinit var audioManager: AudioManager
    @Mock lateinit var inflater: LayoutInflater
    @Mock lateinit var params: WindowManager.LayoutParams
    @Mock lateinit var mediaPlayerFabric: MediaPlayerFabric

    @Mock lateinit var okButton: Button
    @Mock lateinit var view: View
    @Mock lateinit var textView: TextView

    @Mock lateinit var player: MediaPlayer

    lateinit var presenter: AlertViewPresenter

    @Before fun setUp() {
        `when`(context.getString(eq(R.string.remain))).thenReturn("OK")
        `when`(inflater.inflate(eq(R.layout.layout_alert_notification), any())).thenReturn(view)
        `when`(view.findViewById(eq(R.id.okBtn))).thenReturn(okButton)
        `when`(view.findViewById(eq(R.id.remainingTv))).thenReturn(textView)
        `when`(mediaPlayerFabric.getNewMediaPlayer()).thenReturn(player);

        presenter = AlertViewPresenterImpl(context, audioManager, mediaPlayerFabric,
                windowManager, inflater, params)
    }

    @Test fun initView_setOkButtonListener() {
        presenter.initView()

        verify(okButton).setOnClickListener(any(View.OnClickListener::class.java))
    }

    @Test fun hideAlert_removeViewFromWindowManager() {
        presenter.hideAlert()

        verify(windowManager).removeViewImmediate(eq(view))
    }

    @Test fun showLowBatteryAlert_addViewToWindow() {
        presenter.showLowBatteryAlert(anyInt())

        verify(windowManager).addView(eq(view), any(WindowManager.LayoutParams::class.java))
    }

    @Test fun showLowBatteryAlert_preparePlayerAndPlayAudio() {
        presenter.showLowBatteryAlert(anyInt())

        verify(player).setOnSeekCompleteListener(any(OnSeekCompleteListener::class.java))
        verify(player).setOnCompletionListener(any(OnCompletionListener::class.java))
        verify(player).start()
    }

    @Test fun onSeekComplete_stopMediaPlayer() {
        val mediaPlayer = mock(MediaPlayer::class.java)
        doAnswer {
            invocation: InvocationOnMock ->
            val listener= invocation.arguments[0] as OnSeekCompleteListener
            listener.onSeekComplete(mediaPlayer)

        }.`when`(player).setOnSeekCompleteListener(any(OnSeekCompleteListener::class.java))

        presenter.showLowBatteryAlert(anyInt())

        verify(mediaPlayer).stop()
    }

    @Test fun onCompletionListener_releaseMediaPlayer() {
        val mediaPlayer = mock(MediaPlayer::class.java)
        doAnswer {
            invocation: InvocationOnMock ->
            val listener= invocation.arguments[0] as OnCompletionListener
            listener.onCompletion(mediaPlayer)

        }.`when`(player).setOnCompletionListener(any(OnCompletionListener::class.java))

        presenter.showLowBatteryAlert(anyInt())

        verify(mediaPlayer).release()
    }

    @Test fun onOkButtonClick_hideAlert() {
        doAnswer {
            invocation: InvocationOnMock ->
            val listener= invocation.arguments[0] as View.OnClickListener
            listener.onClick(any())

        }.`when`(okButton).setOnClickListener(any(View.OnClickListener::class.java))

        presenter.initView()

        verify(windowManager).removeViewImmediate(eq(view))
    }

}