package com.j380.alarm.injection.component

import com.j380.alarm.injection.annotation.PerActivity
import com.j380.alarm.injection.module.PermissionActivityModule
import com.j380.alarm.permissions.PermissionActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(PermissionActivityModule::class))
interface PermissionActivityComponent {

    fun inject(activity: PermissionActivity)
}