package com.j380.alarm.interactor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.j380.alarm.service.BatteryService

class IntentInteractorImpl(val context: Context) : IntentInteractor {

    override fun getRequestPermissionIntent(): Intent {
        val uri = Uri.parse("package:" + context.packageName)
        return Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
    }

    override fun getStartBatteryServiceIntent() = Intent(context, BatteryService::class.java)
}