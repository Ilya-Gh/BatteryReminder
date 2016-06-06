package com.j380.alarm.presenter

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.j380.alarm.interactor.IntentInteractorImpl
import com.j380.alarm.view.PermissionView

class PermissionPresenterImpl(val context: Context, val view: PermissionView) : PermissionPresenter {

    val REQUEST_CODE = 1;

    //TODO add dagger as a DI(for test purpose)
    val intentInteractor = IntentInteractorImpl(context)

    override fun onCreate() {
        checkDrawOverlayPermission()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(context)) {
                startBatteryService()
            }
        }
    }

    private fun checkDrawOverlayPermission() {
        if (!Settings.canDrawOverlays(context)) {
            view.startActivityForResult(intentInteractor.getRequestPermissionIntent(), REQUEST_CODE);
        } else {
            startBatteryService()
        }
    }

    private fun startBatteryService() {
        view.startService(intentInteractor.getStartBatteryServiceIntent())

    }
}