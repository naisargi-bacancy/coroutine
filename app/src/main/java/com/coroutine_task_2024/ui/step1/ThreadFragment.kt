package com.coroutine_task_2024.ui.step1

import android.util.Log
import android.view.View
import com.coroutine_task_2024.base.BaseFragment
import com.coroutine_task_2024.R
import com.coroutine_task_2024.databinding.FragmentThreadBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

@AndroidEntryPoint
class ThreadFragment() : BaseFragment<FragmentThreadBinding, ThreadViewModel>() {

    private lateinit var binding: FragmentThreadBinding
    private val TAG = "Step1Fragment"
    override val layoutResourceId: Int
        get() = R.layout.fragment_thread

    override val viewModelClass: Class<ThreadViewModel>
        get() = ThreadViewModel::class.java

    override val bindingVariable: Int
        get() = 0

    override fun setUpViews() {
        binding = getViewDataBinding()
        binding.updateCounter.setOnClickListener {
            updateCounter()
        }
        binding.btnExecuteTask.setOnClickListener {
            executeLongRunningTask()
        }
        binding.btnExecuteTaskThread.setOnClickListener {
            threadUsage()
        }
    }

    override fun initObservers() {
        //TODO("Not yet implemented")
    }

    private fun updateCounter() {
        "${
            binding.txtCounter.text.toString().toInt() + 1
        }".also { binding.txtCounter.text = it }
        Log.e(TAG, "updateCounter")
        Log.e(TAG, "Thread: ${Thread.currentThread().name}")
    }

    /**
     * long running task
     */
    private fun executeLongRunningTask() {
        Log.e("!_@_", "executeLongRunningTask begin")
        for (i in 1..1000000000L) {
            for (j in 1..10L) {

            }
        }
        Log.e("!_@_", "executeLongRunningTask end")
    }

    /**
     * thread execution
     */
    fun threadUsage() {
        thread(start = true) {
            executeLongRunningTask()
        }
    }

    /**
     * coroutine
     */
    fun coroutineUsage(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("!_@_", "1- ${Thread.currentThread().name}")
        }
        MainScope().launch(Dispatchers.Default) {
            Log.e("!_@_", "2- ${Thread.currentThread().name}")
        }
        GlobalScope.launch(Dispatchers.Main) {
            Log.e("!_@_", "3- ${Thread.currentThread().name}")
        }
    }
}