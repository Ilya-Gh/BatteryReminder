package com.j380.alarm.permissions

import android.content.Intent
import com.j380.alarm.intent.IntentInteractor
import com.j380.alarm.permissions.PermissionInteractor
import com.j380.alarm.permissions.PermissionView

class PermissionPresenterImpl(val intentInteractor: IntentInteractor,
        var permissionInteractor: PermissionInteractor) : PermissionPresenter {

    val REQUEST_CODE = 1;

    private lateinit var view: PermissionView

    override fun onCreate() {
        checkDrawOverlayPermissionAndStartService()
    }

    override fun setView(view: PermissionView) {
        this.view = view;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_CODE) {
            if (isDrawOverlayPermissionGranted()) {
                startBatteryServiceAndCloseActivity()
            }
        }
    }

    private fun checkDrawOverlayPermissionAndStartService() {
        if (isDrawOverlayPermissionGranted()) {
            startBatteryServiceAndCloseActivity()
        } else {
            view.startActivityForResult(intentInteractor.getRequestPermissionIntent(),
                    REQUEST_CODE);
        }
    }

    private fun startBatteryServiceAndCloseActivity() {
        view.startService(intentInteractor.getStartBatteryServiceIntent())
        view.closeActivity()
    }

    private fun isDrawOverlayPermissionGranted(): Boolean {
        return permissionInteractor.isDrawOverlayPermissionGranted()
    }
}