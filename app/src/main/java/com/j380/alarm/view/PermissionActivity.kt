package com.j380.alarm.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.j380.alarm.BatteryReminderApplication
import com.j380.alarm.injection.component.PermissionActivityComponent
import com.j380.alarm.presenter.PermissionPresenter
import javax.inject.Inject

class PermissionActivity : AppCompatActivity(), PermissionView {

    @Inject lateinit var presenter: PermissionPresenter
    private lateinit var component: PermissionActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
        presenter.onCreate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun closeActivity() = finish()

    private fun initInjector() {
        component = BatteryReminderApplication.appComponent.plus()
        component.inject(this)
    }
}
