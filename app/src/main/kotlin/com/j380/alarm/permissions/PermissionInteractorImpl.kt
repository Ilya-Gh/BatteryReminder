package com.j380.alarm.permissions

import android.content.Context
import android.provider.Settings

class PermissionInteractorImpl(var context: Context) : PermissionInteractor {
    override fun isDrawOverlayPermissionGranted(): Boolean {
        return Settings.canDrawOverlays(context)
    }
}