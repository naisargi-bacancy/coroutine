package com.coroutine_task_2024

import android.app.Application
import android.util.Log
import com.coroutine_task_2024.multithreading.DefaultExecutorSupplier
import com.coroutine_task_2024.repository.BaseDataSource
import com.coroutine_task_2024.utils.firebase.FRCHelper
import com.google.android.gms.tasks.Task
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.installations.InstallationTokenResult
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException

class CoroutineApplication : Application() {

    private val TAG = "CoroutineApplication"

    private val onConfigUpdate: FRCUpdateListener by lazy { FRCUpdateListener() }

    override fun onCreate() {
        super.onCreate()

        Log.e(TAG, "setCrashlyticsCollectionEnabled")
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

        //add firebase config update listener
        FirebaseRemoteConfig.getInstance().addOnConfigUpdateListener(onConfigUpdate)

        //perform task in background
        DefaultExecutorSupplier.instance.forBackgroundTasks().execute {
            performBackgroundExecution()
        }
    }

    private fun performBackgroundExecution() {
        initABTesting()
    }

    private fun initABTesting() {
        //Latest Token Generation Code
        FirebaseInstallations.getInstance().getToken( /* forceRefresh */false)
            .addOnCompleteListener { task: Task<InstallationTokenResult?> ->
                if (task.isSuccessful && task.result != null) {
                    Log.e(
                        "Installations",
                        "Installation auth token: " + task.result?.token
                    )
                } else {
                    Log.e("Installations", "Unable to get Installation auth token")
                }
            }
    }

    open class FRCUpdateListener : ConfigUpdateListener {

        private val TAG = "FRCUpdateListener"

        override fun onUpdate(configUpdate: ConfigUpdate) {
            Log.e(TAG, "onUpdate called with updated keys..")

            // activate config
            FirebaseRemoteConfig.getInstance().activate()
            // read activated config, parse all keys from local
            FRCHelper.getAndUpdateRemoteConfig({
                Log.e("frcUpdateListener", "Parsing complete")
            }, BaseDataSource.Type.LOCAL)
        }

        override fun onError(error: FirebaseRemoteConfigException) {
            Log.e(TAG, "error caught : ${error.message}")
        }

    }
}