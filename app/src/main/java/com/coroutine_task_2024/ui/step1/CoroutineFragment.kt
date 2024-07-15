package com.coroutine_task_2024.ui.step1

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.coroutine_task_2024.base.BaseFragment
import com.coroutine_task_2024.R
import com.coroutine_task_2024.databinding.FragmentCoroutineBinding
import com.coroutine_task_2024.multithreading.DefaultExecutorSupplier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineFragment() : BaseFragment<FragmentCoroutineBinding, CoroutineViewModel>() {

    private lateinit var binding: FragmentCoroutineBinding
    private val TAG = "CoroutineFragment"
    override val layoutResourceId: Int
        get() = R.layout.fragment_coroutine

    override val viewModelClass: Class<CoroutineViewModel>
        get() = CoroutineViewModel::class.java

    override val bindingVariable: Int
        get() = 0

    override fun setUpViews() {
        binding = getViewDataBinding()
        binding.btnFbLaunch.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                printFollowersLaunch()
            }
        }
        binding.btnFbAsync.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                printFollowersAsync()
            }
        }
        binding.btnParallelExecution.setOnClickListener {
            printFollowers()
        }
        binding.btnJob.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                understandJobHierarchy()
            }
        }
        binding.btnLifeCycleViewModel.setOnClickListener {
            lifecycleScope.launch {
                getViewModel().createCoroutine()
            }
        }
        coroutineUsage()
    }

    override fun initObservers() {
        //TODO("Not yet implemented")
    }

    /**
     * coroutine scope and lifetime
     */
    private fun coroutineUsage() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("!_@_", "1- ${Thread.currentThread().name}")
        }
        MainScope().launch(Dispatchers.Default) {
            Log.e("!_@_", "2- ${Thread.currentThread().name}")
        }
        GlobalScope.launch(Dispatchers.Main) {
            Log.e("!_@_", "3- ${Thread.currentThread().name}")
        }
        /* val handler = CoroutineExceptionHandler { context, exception ->
             Log.e(TAG, "Exception got $exception")
         }
         GlobalScope.launch(handler) {
             throw Throwable("Some exception")
         }*/

        CoroutineScope(Dispatchers.IO).launch {
            demoWithContext()
        }

        Log.e(TAG, "End..")


        /*CoroutineScope(Dispatchers.Default).launch {
            Log.e(TAG,"coroutine begins..")
            delay(2000)
            Log.e(TAG,"coroutine ends..")
        }*/

        DefaultExecutorSupplier.instance.forBackgroundTasks().execute {
            //Code to run

        }

        DefaultExecutorSupplier.instance.forIOTasks().execute{
            //IO task
            getFbFollowers()
        }
    }

    private suspend fun demoWithContext() {
        Log.e(TAG, "Before")
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.e(TAG, "Inside")
        }
        Log.e(TAG, "After")
    }

    private fun printFollowers() {
        CoroutineScope(Dispatchers.IO).launch {
            val fb = async { getFbFollowers() }
            val insta = async { getInstaFollowers() }
            Log.e(TAG, "Fb followers: ${fb.await()} Insta followers: ${insta.await()}")
        }
    }

    private suspend fun printFollowersLaunch() {
        var fbFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollowers()
        }
        job.join()
        Log.e(TAG, "Fb followers:$fbFollowers")
    }

    private suspend fun printFollowersAsync() {
        val deferred = CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()
        }
        Log.e(TAG, "Fb followers:${deferred.await()}")
    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 10
    }

    private suspend fun getInstaFollowers(): Int {
        delay(3000)
        return 56
    }

    private suspend fun understandJobHierarchy() {
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            Log.e(TAG, "Parent job started")

            val childJob = launch {
                Log.e(TAG, "Child job started")
                delay(5000)
                Log.e(TAG, "Child job ended")
            }
            delay(3000)/*Log.e(TAG, "Child job cancelled")
            childJob.cancel()*/
            Log.e(TAG, "Parent job ended")
        }

        /*delay(1000)
        parentJob.cancel()*/
        parentJob.join()
        Log.e(TAG, "Parent job completed")
    }
}