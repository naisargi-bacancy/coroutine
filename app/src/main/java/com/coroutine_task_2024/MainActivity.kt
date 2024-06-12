package com.coroutine_task_2024

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutine_task_2024.R

class MainActivity : AppCompatActivity() {


    private lateinit var txtCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initControls()
    }

    private fun initControls() {
        txtCounter = findViewById(R.id.txtCounter)
    }

    fun updateCounter(view: View) {
        "${txtCounter.text.toString().toInt() + 1}".also { txtCounter.text = it }
    }

    /**
     * step 1 - usage of thread
     */
    public fun executeTask1() {

    }

    fun displayTimer() {

    }
}