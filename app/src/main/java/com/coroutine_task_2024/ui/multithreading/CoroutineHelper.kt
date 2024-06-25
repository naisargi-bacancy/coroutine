package com.coroutine_task_2024.ui.multithreading

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object CoroutineHelper {
    const val TAG = "CoroutineHelper"
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Caught an exception in coroutine :" + throwable.stackTraceToString())
    }
    val dispatcherDefaultCoroutineScope: CoroutineScope
        get() = _dispatcherDefaultCoroutineScope
    val dispatcherIOCoroutineScope: CoroutineScope
        get() = _dispatcherIOCoroutineScope
    val dispatcherMainCoroutineScope: CoroutineScope
        get() = _dispatcherMainCoroutineScope

    private val _dispatcherDefaultCoroutineScope: CoroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.Default + coroutineExceptionHandler)
    }
    private val _dispatcherIOCoroutineScope: CoroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.IO + coroutineExceptionHandler)
    }
    private val _dispatcherMainCoroutineScope: CoroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.Main + coroutineExceptionHandler)
    }
}