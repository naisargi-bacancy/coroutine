package com.coroutine_task_2024.ui.home

import com.coroutine_task_2024.R
import com.coroutine_task_2024.base.BaseViewModel
import com.coroutine_task_2024.utils.SingleLiveEvent

class HomeViewModel : BaseViewModel() {

    val navigateToFragment = SingleLiveEvent<Int?>()

    fun onThreadButtonClick() {
        navigateToFragment.setValue(R.id.action_homeFragment_to_threadFragment)
    }

    fun onCoroutineButtonClick() {
        navigateToFragment.setValue(R.id.action_homeFragment_to_coroutineFragment)
    }

    fun resetNavigationValue() {
        navigateToFragment.setValue(null)
    }

}