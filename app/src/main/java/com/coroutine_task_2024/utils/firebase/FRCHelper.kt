package com.coroutine_task_2024.utils.firebase

import android.util.Log
import com.coroutine_task_2024.multithreading.CoroutineHelper
import com.coroutine_task_2024.repository.BaseDataSource
import com.coroutine_task_2024.repository.firebase.FRCLocalDataSource
import com.coroutine_task_2024.repository.firebase.FRCRemoteDataSource
import com.coroutine_task_2024.repository.firebase.FRCRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object FRCHelper {
    //manage scope - Local/remote
    //call config

    private val TAG = "FRCHelper"

    private val coroutineScope = CoroutineHelper.dispatcherIOCoroutineScope

    private val frcRepository: FRCRepository by lazy {
        FRCRepository(
            FRCRemoteDataSource(FirebaseABIntegration.remoteConfig),
            FRCLocalDataSource(FirebaseABIntegration.remoteConfig),
            Dispatchers.IO
        )
    }

    fun getAndUpdateRemoteConfig(
        listener: () -> Unit,
        dataSourceType: BaseDataSource.Type
    ) {
        Log.e(TAG, "getAndUpdateRemoteConfig fetching.. $dataSourceType")
        coroutineScope.launch {
            try {
                frcRepository.getAndUpdateRemoteConfig(dataSourceType)
            } catch (e: Exception) {
                Log.e(TAG, "getAndUpdateRemoteConfig Failed: " + e.message)
            }
            listener.invoke()
        }
    }
}