package com.coroutine_task_2024.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var navigateToStep1: MutableLiveData<Boolean> = MutableLiveData(false)
    var navigateToCoroutine: MutableLiveData<Boolean> = MutableLiveData(false)
    fun onThreadButtonClick() {
        navigateToStep1.postValue(true)
    }

    fun onCoroutineButtonClick() {
        navigateToCoroutine.postValue(true)
    }

    fun onNavigationComplete() {
        navigateToStep1.postValue(false)
        navigateToCoroutine.postValue(false)
    }
}