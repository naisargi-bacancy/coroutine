package com.coroutine_task_2024.repository.firebase

import android.util.Log
import com.coroutine_task_2024.repository.BaseDataSource
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import kotlinx.coroutines.suspendCancellableCoroutine

class FRCRemoteDataSource(private val firebaseRemoteConfig: FirebaseRemoteConfig) :
    BaseDataSource<Map<String, FirebaseRemoteConfigValue>>() {
    private val TAG = "FRCRemoteDataSource"
    override suspend fun fetchData(): Map<String, FirebaseRemoteConfigValue> =
        suspendCancellableCoroutine { continuation ->
            //firebase fetch call
            firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener {
                Log.e(
                    TAG,
                    "addOnCompleteListener " + "continuation.isActive " + continuation.isActive
                )
                if (continuation.isActive) {
                    val configMap = firebaseRemoteConfig.all
                    continuation.resumeWith(Result.success(configMap))
                }
            }
        }
}