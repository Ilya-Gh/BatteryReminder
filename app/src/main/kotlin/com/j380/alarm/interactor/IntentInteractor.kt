package com.j380.alarm.interactor

import android.content.Intent

interface IntentInteractor {

    open fun getRequestPermissionIntent(): Intent

    open fun getStartBatteryServiceIntent(): Intent
}