package com.j380.alarm.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import com.j380.alarm.BatteryService

class PermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkDrawOverlayPermission()

    }

    var REQUEST_CODE = 10;

    fun checkDrawOverlayPermission() {
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(this)) {
            /** if not construct intent to request permission */
            intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + packageName));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,  data: Intent) {
        /** check if received result code
        is equal our requested code for draw permission  */
        if (requestCode == REQUEST_CODE) {
            /** if so check once again if we have permission */
            if (Settings.canDrawOverlays(this)) {
                // continue here - permission was granted
                startService()
            }
        }
    }

    fun startService() {
        val batteryIntent = Intent(this, BatteryService::class.java)
        startService(batteryIntent)
    }
}
