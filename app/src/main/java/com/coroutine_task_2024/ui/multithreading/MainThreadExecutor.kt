package com.coroutine_task_2024.ui.multithreading

import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainThreadExecutor {
    private val TAG = "MainThreadExecutor"
    fun execute(task: Runnable?): Job {
        Log.e(TAG, "" + task.hashCode() + " queuing")

        return CoroutineHelper.dispatcherMainCoroutineScope.launch {
            Log.e(TAG, "" + task.hashCode() + " executing")
            task?.run()
            Log.e(TAG, "" + task.hashCode() + " done")
        }
    }
}