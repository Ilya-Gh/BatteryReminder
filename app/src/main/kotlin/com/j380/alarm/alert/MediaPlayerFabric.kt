package com.j380.alarm.alert

import android.media.MediaPlayer

interface MediaPlayerFabric {

    fun getNewMediaPlayer(): MediaPlayer

}