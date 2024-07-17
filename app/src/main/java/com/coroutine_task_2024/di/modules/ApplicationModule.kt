package com.coroutine_task_2024.di.modules

import com.coroutine_task_2024.CoroutineApplication
import com.coroutine_task_2024.repository.firebase.FRCLocalDataSource
import com.coroutine_task_2024.repository.firebase.FRCRemoteDataSource
import com.coroutine_task_2024.repository.firebase.FRCRepository
import com.coroutine_task_2024.utils.firebase.FRCHelper
import com.coroutine_task_2024.utils.firebase.FirebaseABIntegration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun provideFRCRemoteDataSource(): FRCRemoteDataSource {
        return FRCRemoteDataSource(FirebaseABIntegration.remoteConfig)
    }

    @Provides
    @Singleton
    fun provideFRCLocalDataSource(): FRCLocalDataSource {
        return FRCLocalDataSource(FirebaseABIntegration.remoteConfig)
    }

    @Provides
    fun provideFRCHelper(frcRepository: FRCRepository): FRCHelper {
        return FRCHelper(frcRepository)
    }

    @Provides
    fun provideFRCListener(): CoroutineApplication.FRCUpdateListener {
        return CoroutineApplication.FRCUpdateListener()
    }
}