package com.coroutine_task_2024.repository.firebase

import android.util.Log
import com.coroutine_task_2024.repository.BaseDataSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FRCRepository @Inject constructor(
    private val remoteDataSource: FRCRemoteDataSource,
    private val localDataSource: FRCLocalDataSource
) {
    suspend fun getAndUpdateRemoteConfig(dataSourceType: BaseDataSource.Type) {
        val configList = mapDataSource(dataSourceType).fetchData()
        //TODO process configList
        //use dispatcher to process the list
        Log.e("getAndUpdateRemoteConfig", " configlist:${configList.size}")

    }

    private fun mapDataSource(dataSourceType: BaseDataSource.Type) = when (dataSourceType) {
        BaseDataSource.Type.LOCAL -> localDataSource
        BaseDataSource.Type.REMOTE -> remoteDataSource
    }
}