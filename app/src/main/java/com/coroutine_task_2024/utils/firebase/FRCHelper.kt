package com.coroutine_task_2024.utils.firebase

import android.util.Log
import com.coroutine_task_2024.multithreading.CoroutineHelper
import com.coroutine_task_2024.repository.BaseDataSource
import com.coroutine_task_2024.repository.firebase.FRCRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FRCHelper @Inject constructor(val frcRepository: FRCRepository) {
    //manage scope - Local/remote
    //call config

    private val TAG = "FRCHelper"

    private val coroutineScope = CoroutineHelper.dispatcherIOCoroutineScope

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