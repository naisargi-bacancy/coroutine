package com.coroutine_task_2024.ui.home

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.coroutine_task_2024.BR
import com.coroutine_task_2024.R
import com.coroutine_task_2024.base.BaseFragment
import com.coroutine_task_2024.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val TAG = "HomeFragment"
    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setUpViews() {
        /*lifecycleScope.launch {
            val s1 = someTask()
            Log.e(TAG, s1)
        }*/

        /*while (true) {
            //Assume this is Simulating UI tasks like a animation or progress rotation, ongoing UI
            //Our main thread should be always busy now
            Log.e(TAG, "I AM THE UI SIMULATION TASK")
        }*/
    }

    private suspend fun someTask(): String {
        //I DON'T BELIEVE YOU, SHOW ME THE CURRENT THREAD
        Log.e(TAG, "Current running thread ${Thread.currentThread().name}")
        delay(2000) // Simulating a delay for demonstration
        return "Result from the task"
    }

    override fun initObservers() {
        getViewModel().navigateToFragment.observe(this) { action ->
            action?.let {
                if (findNavController().currentDestination?.id == R.id.homeFragment) {
                    findNavController().navigate(action, null)
                    // Reset the value to prevent re-navigation
                    getViewModel().resetNavigationValue()
                }
            }
        }
    }
}