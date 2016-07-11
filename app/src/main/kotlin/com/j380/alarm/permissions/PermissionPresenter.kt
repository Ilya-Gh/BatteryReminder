package com.j380.alarm.permissions

import android.content.Intent
import com.j380.alarm.permissions.PermissionView

interface PermissionPresenter {

    fun onCreate()

    fun setView(view: PermissionView)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
}
