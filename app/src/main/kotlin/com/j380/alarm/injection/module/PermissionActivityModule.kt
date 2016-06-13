package com.j380.alarm.injection.module

import android.content.Context
import com.j380.alarm.injection.annotation.PerActivity
import com.j380.alarm.interactor.IntentInteractor
import com.j380.alarm.presenter.PermissionPresenter
import com.j380.alarm.presenter.PermissionPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PermissionActivityModule {

    @Provides
    @PerActivity
    fun providePermissionPresenter(context: Context, interactor: IntentInteractor):
            PermissionPresenter = PermissionPresenterImpl (context, interactor)

}

