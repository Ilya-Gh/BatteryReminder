package com.j380.alarm.injection.module

import com.j380.alarm.injection.annotation.PerActivity
import com.j380.alarm.interactor.IntentInteractor
import com.j380.alarm.interactor.PermissionInteractor
import com.j380.alarm.presenter.PermissionPresenter
import com.j380.alarm.presenter.PermissionPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PermissionActivityModule {

    @Provides
    @PerActivity
    fun providePermissionPresenter(intent: IntentInteractor, permission: PermissionInteractor):
            PermissionPresenter = PermissionPresenterImpl (intent, permission)

}

