package com.coroutine_task_2024.ui.home

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.coroutine_task_2024.BR
import com.coroutine_task_2024.BaseFragment
import com.coroutine_task_2024.R
import com.coroutine_task_2024.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setUpViews() {

    }

    override fun initObservers() {
        getViewModel().navigateToStep1.observe(this) { navigate ->
            if (navigate) {
                findNavController().navigate(R.id.action_homeFragment_to_step1Fragment)
                getViewModel().onNavigationComplete()
            }
        }
        getViewModel().navigateToCoroutine.observe(this) { navigate ->
            if (navigate) {
                findNavController().navigate(R.id.action_homeFragment_to_coroutineFragment)
                getViewModel().onNavigationComplete()
            }
        }
    }
}