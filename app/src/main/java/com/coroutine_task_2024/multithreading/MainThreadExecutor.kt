package com.coroutine_task_2024.multithreading

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainThreadExecutor {
    private val TAG = "MainThreadExecutor"
    fun execute(task: suspend () -> Unit) {
        Log.e(TAG, "" + task.hashCode() + " queuing")
        //ThreadPoolKUtils.runOnUiThread(task)
        CoroutineHelper.dispatcherMainCoroutineScope.launch {
            Log.e(TAG, "" + task.hashCode() + " executing")
            task()
            Log.e(TAG, "" + task.hashCode() + " done")
        }
    }

    fun executeAfter(task: suspend () -> Unit, delayInTime: Long) {
        CoroutineHelper.dispatcherMainCoroutineScope.launch {
            delay(delayInTime)
            task()
        }
    }
}