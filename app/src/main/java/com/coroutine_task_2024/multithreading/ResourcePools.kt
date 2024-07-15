package com.coroutine_task_2024.multithreading

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService

object ThreadPoolKUtils {

    @JvmStatic
    val handler: Handler = Handler(Looper.getMainLooper())

    fun runOnUiThread(runnable: Runnable?) {
        runnable?.let {
            //run if already on UI thread
            if (Looper.myLooper() == Looper.getMainLooper()) {
                it.run()
            } else {
                //post on UI thread if called from Non-UI thread
                handler.post(it)
            }
        }
    }
}