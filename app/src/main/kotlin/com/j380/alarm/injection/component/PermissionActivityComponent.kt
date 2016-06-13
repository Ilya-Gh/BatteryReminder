package com.j380.alarm.injection.component

import com.j380.alarm.injection.annotation.PerActivity
import com.j380.alarm.injection.module.PermissionActivityModule
import com.j380.alarm.presenter.PermissionPresenter
import com.j380.alarm.view.PermissionActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(PermissionActivityModule::class))
interface PermissionActivityComponent {

    fun inject(activity: PermissionActivity)

    fun permissionPresenter(): PermissionPresenter
}