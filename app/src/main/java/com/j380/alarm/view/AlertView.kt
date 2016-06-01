package com.j380.alarm.view

import android.app.Service
import android.content.Context
import android.graphics.PixelFormat
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.Button
import com.j380.alarm.R


class AlertView(context: Context) {
    private val context = context
    private val windowManager = context.getSystemService(Service.WINDOW_SERVICE) as WindowManager
    private val inflater = context.getSystemService(
            Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val view = inflater.inflate(R.layout.aler_notification, null)
    private val button = view.findViewById(R.id.button) as Button
    private val player = MediaPlayer.create(context, R.raw.low)
    private val audioManager = context.getSystemService(Service.AUDIO_SERVICE) as AudioManager

    val lParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT,
            LayoutParams.TYPE_SYSTEM_ALERT,
            LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT)

    fun initView() {

        lParams.gravity = Gravity.CENTER

        button.setOnClickListener({
            close()
        })

        player.setOnSeekCompleteListener({
            mediaPlayer: MediaPlayer ->
            mediaPlayer.stop()
        })


        player.setOnCompletionListener({
            mediaPlayer: MediaPlayer ->
            mediaPlayer.release()
        })
    }

    fun show(batteryLevel: Float) {

        //        ((TextView) mView.findViewById(R.id.remain)).setText(String.Companion.format(getString(R.string.remain), (int) mBatteryPct));
        //

        windowManager.addView(view, lParams)
    }

    fun playAudio() {
        val volume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        player.setVolume(volume.toFloat(), volume.toFloat())
        player.start()
    }

    fun close() {
        windowManager.removeViewImmediate(view)
    }

}