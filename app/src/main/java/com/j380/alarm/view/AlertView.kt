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
import android.widget.TextView
import com.j380.alarm.R


class AlertView(context: Context) {

    private val context = context
    private val player = MediaPlayer.create(context, R.raw.low)
    private val audioManager = context.getSystemService(Service.AUDIO_SERVICE) as AudioManager
    private val windowManager = context.getSystemService(Service.WINDOW_SERVICE) as WindowManager
    private val inflater = context.getSystemService(
            Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val view = inflater.inflate(R.layout.layout_alert_notification, null)
    private val okButton = view.findViewById(R.id.okBtn) as Button
    private val remainingTv = view.findViewById(R.id.remainingTv) as TextView

    private val lParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT,
            LayoutParams.TYPE_SYSTEM_ALERT,
            LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT)

    fun initView() {

        lParams.gravity = Gravity.CENTER

        okButton.setOnClickListener({
            close()
        })
        setPlayerListeners()
    }

    private fun setPlayerListeners() {
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
        remainingTv.setText(String.format(context.getString(R.string.remain), batteryLevel));
        windowManager.addView(view, lParams)
    }

    fun playAudio() {
        setVolumeToPlayer(player, getVolume())
        player.start()
    }

    private fun getVolume() = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

    private fun setVolumeToPlayer(player: MediaPlayer, volume: Int) {
        player.setVolume(volume.toFloat(), volume.toFloat())
    }

    public fun close() {
        windowManager.removeViewImmediate(view)
    }

}