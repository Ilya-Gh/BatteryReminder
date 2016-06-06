package com.j380.alarm.view

import android.content.ComponentName
import android.content.Intent

interface PermissionView {

    fun startActivityForResult(intent: Intent, code: Int)

    fun startService(intent: Intent): ComponentName

}