package com.coroutine_task_2024.repository.firebase

import com.coroutine_task_2024.repository.BaseDataSource
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import kotlinx.coroutines.suspendCancellableCoroutine

class FRCLocalDataSource(private val firebaseRemoteConfig: FirebaseRemoteConfig) :
    BaseDataSource<Map<String, FirebaseRemoteConfigValue>>() {
    override suspend fun fetchData(): Map<String, FirebaseRemoteConfigValue> =
        suspendCancellableCoroutine { continuation ->
            // to make job cancellable
            continuation.invokeOnCancellation { }
            // respond with local data
            continuation.resumeWith(Result.success(firebaseRemoteConfig.all))
        }
}