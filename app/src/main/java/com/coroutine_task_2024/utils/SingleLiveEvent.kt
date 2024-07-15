package com.coroutine_task_2024.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val observers = mutableListOf<Observer<in T>>()
    override fun setValue(value: T) {
        super.setValue(value)
        observers.forEach { it.onChanged(value) }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { t ->
            if (observers.contains(observer)) {
                observer.onChanged(t)
            }
        })
        observers.add(observer)
    }
}