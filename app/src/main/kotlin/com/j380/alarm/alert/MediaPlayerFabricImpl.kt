package com.j380.alarm.alert

import android.content.Context
import android.media.MediaPlayer
import com.j380.alarm.R

class MediaPlayerFabricImpl(val context: Context) : MediaPlayerFabric {
    override fun getNewMediaPlayer(): MediaPlayer {
        return MediaPlayer.create(context, R.raw.low)
    }
}