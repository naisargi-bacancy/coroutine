package com.coroutine_task_2024.utils.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


object FirebaseABIntegration {

    private const val MINIMUM_FETCH_INTERVAL_IN_SEC: Long = 12 * 60 * 60L

    val remoteConfig: FirebaseRemoteConfig by lazy {

        //On Debug - 1 seconds, On Release - 3600 seconds
        val configSetting = FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(
            //if (BuildConfig.DEBUG) {
                1L
//            } else {
//                MINIMUM_FETCH_INTERVAL_IN_SEC
//            }
        ).build()
        FirebaseRemoteConfig.getInstance().setConfigSettingsAsync(configSetting)

        //return the instance
        FirebaseRemoteConfig.getInstance()
    }
}