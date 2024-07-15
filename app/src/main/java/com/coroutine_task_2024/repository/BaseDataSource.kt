package com.coroutine_task_2024.repository

abstract class BaseDataSource<out T> {
    enum class Type {
        REMOTE,
        LOCAL
    }

    abstract suspend fun fetchData(): T
}