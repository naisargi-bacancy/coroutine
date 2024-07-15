package com.coroutine_task_2024.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

/**
 * MVVM base class for all fragment viewmodels
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val TAG: String get() = this.javaClass.simpleName.toString() + ":" + hashCode()

    /**
     * returns the current viewmodel scope
     */
    val vmCoroutineScope: CoroutineScope
        get() = viewModelScope

}