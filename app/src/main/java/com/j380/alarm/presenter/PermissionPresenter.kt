package com.j380.alarm.presenter

import android.content.Intent

interface PermissionPresenter {

    fun onCreate()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
}
