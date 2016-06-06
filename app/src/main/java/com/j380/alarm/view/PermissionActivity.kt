package com.j380.alarm.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.j380.alarm.presenter.PermissionPresenter
import com.j380.alarm.presenter.PermissionPresenterImpl

class PermissionActivity : AppCompatActivity(), PermissionView {

    lateinit var presenter: PermissionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PermissionPresenterImpl(applicationContext, this)
        presenter.onCreate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

}
