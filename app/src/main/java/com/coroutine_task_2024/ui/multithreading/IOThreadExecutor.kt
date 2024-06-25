package com.coroutine_task_2024.ui.multithreading

import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IOThreadExecutor {
    private val TAG = "IOThreadExecutor"
    fun execute(task: Runnable?): Job {
        Log.e(TAG, "" + task.hashCode() + " queuing")

        return CoroutineHelper.dispatcherIOCoroutineScope.launch {
            Log.e(TAG, "" + task.hashCode() + " executing")
            task?.run()
            Log.e(TAG, "" + task.hashCode() + " done")
        }
    }
}