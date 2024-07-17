package com.coroutine_task_2024.ui.splash

import android.util.Log
import com.coroutine_task_2024.base.BaseViewModel
import com.coroutine_task_2024.repository.BaseDataSource
import com.coroutine_task_2024.utils.firebase.FRCHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var frcHelper: FRCHelper

    private val frcUpdateListener: () -> Unit = {
        //call config Api to refresh app with the latest config values
        Log.e("frcUpdateListener","call config api..")
    }

    fun callFirebaseRemoteConfig() {
        //call config
        frcHelper.getAndUpdateRemoteConfig(frcUpdateListener, BaseDataSource.Type.REMOTE)
    }
}