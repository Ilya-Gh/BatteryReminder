package com.j380.alarm.permissions

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.j380.alarm.BatteryReminderApplication
import com.j380.alarm.permissions.PermissionPresenter
import javax.inject.Inject

class PermissionActivity : AppCompatActivity(), PermissionView {

    @Inject lateinit var presenter: PermissionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
        presenter.setView(this)
        presenter.onCreate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun closeActivity() = finish()

    private fun initInjector() {
        BatteryReminderApplication.appComponent.plusPermissionComponent().inject(this)
    }
}
