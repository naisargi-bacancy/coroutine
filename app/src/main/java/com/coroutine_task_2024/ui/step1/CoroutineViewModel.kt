package com.coroutine_task_2024.ui.step1

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutine_task_2024.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoroutineViewModel @Inject constructor() : BaseViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "Viewmodel Destroyed")
    }

    fun createCoroutine() {
        viewModelScope.launch {
            while (true) {
                delay(500)
                Log.e(TAG, "Hello from viewmodel")
            }
        }
    }
}