package com.j380.alarm.intent

import android.content.Intent

interface IntentInteractor {

    open fun getRequestPermissionIntent(): Intent

    open fun getStartBatteryServiceIntent(): Intent
}