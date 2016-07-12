package com.j380.alarm.alert

import android.content.Context
import android.media.MediaPlayer
import com.j380.alarm.R

class MediaPlayerFactoryImpl(val context: Context) : MediaPlayerFactory {
    override fun getNewMediaPlayer(): MediaPlayer {
        return MediaPlayer.create(context, R.raw.low)
    }
}