package com.j380.alarm.alert

import android.media.MediaPlayer

interface MediaPlayerFactory {

    fun getNewMediaPlayer(): MediaPlayer

}