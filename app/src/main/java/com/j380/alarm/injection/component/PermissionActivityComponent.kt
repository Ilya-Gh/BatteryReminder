package com.j380.alarm.injection.component

import com.j380.alarm.injection.module.PermissionActivityModule
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(PermissionActivityModule::class))
interface PermissionActivityComponent {


}