package com.j380.alarm.presenter

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.j380.alarm.interactor.IntentInteractor
import com.j380.alarm.view.PermissionView

class PermissionPresenterImpl(val context: Context, val intentInteractor: IntentInteractor) :
        PermissionPresenter {

    val REQUEST_CODE = 1;

    private lateinit var view: PermissionView

    override fun onCreate() {
        checkDrawOverlayPermission()
    }

    override fun setView(view: PermissionView) {
        this.view = view;
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
            view.startActivityForResult(intentInteractor.getRequestPermissionIntent(),
                    REQUEST_CODE);
        } else {
            startBatteryService()
        }
    }

    private fun startBatteryService() {
        view.startService(intentInteractor.getStartBatteryServiceIntent())
        view.closeActivity()

    }
}