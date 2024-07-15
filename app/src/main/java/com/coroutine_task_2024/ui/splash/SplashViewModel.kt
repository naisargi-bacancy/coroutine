package com.coroutine_task_2024.ui.splash

import android.util.Log
import com.coroutine_task_2024.base.BaseViewModel
import com.coroutine_task_2024.repository.BaseDataSource
import com.coroutine_task_2024.utils.firebase.FRCHelper

class SplashViewModel : BaseViewModel() {

    private val frcUpdateListener: () -> Unit = {
        //call config Api to refresh app with the latest config values
        Log.e("frcUpdateListener","call config api..")
    }

    fun callFirebaseRemoteConfig() {
        //call config
        FRCHelper.getAndUpdateRemoteConfig(frcUpdateListener, BaseDataSource.Type.REMOTE)
    }
}