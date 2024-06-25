package com.coroutine_task_2024.ui.multithreading

object DefaultExecutorSupplier {
    const val TAG = "DefaultExecutorSupplier"

    @JvmStatic
    val instance: DefaultExecutorSupplier by lazy { this }

    private val backgroundTaskExecutor: BackgroundThreadExecutor = BackgroundThreadExecutor()

    private val mainThreadExecutor: MainThreadExecutor = MainThreadExecutor()

    private val ioTaskExecutor: IOThreadExecutor = IOThreadExecutor()

    /*
     * returns the thread pool executor for background task
     */
    fun forBackgroundTasks(): BackgroundThreadExecutor = backgroundTaskExecutor

    /*
     * returns the thread pool executor for Main task
     */
    fun forMainTasks(): MainThreadExecutor = mainThreadExecutor

    /*
     * returns the thread pool executor for IO task
     */
    fun forIOTasks(): IOThreadExecutor = ioTaskExecutor
}