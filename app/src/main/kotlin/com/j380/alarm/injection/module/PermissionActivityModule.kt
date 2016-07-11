package com.j380.alarm.injection.module

import com.j380.alarm.injection.annotation.PerActivity
import com.j380.alarm.intent.IntentInteractor
import com.j380.alarm.permissions.PermissionInteractor
import com.j380.alarm.permissions.PermissionPresenter
import com.j380.alarm.permissions.PermissionPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PermissionActivityModule {

    @Provides
    @PerActivity
    fun providePermissionPresenter(intent: IntentInteractor, permission: PermissionInteractor):
            PermissionPresenter = PermissionPresenterImpl (intent, permission)

}

