package com.coroutine_task_2024.ui.splash

import androidx.navigation.fragment.findNavController
import com.coroutine_task_2024.BR
import com.coroutine_task_2024.R
import com.coroutine_task_2024.base.BaseFragment
import com.coroutine_task_2024.databinding.FragmentSplashBinding
import com.coroutine_task_2024.multithreading.DefaultExecutorSupplier

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_splash
    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setUpViews() {
        //firebase remote config
        DefaultExecutorSupplier.instance.forBackgroundTasks().execute {
            getViewModel().callFirebaseRemoteConfig()
        }
        //navigate to home fragment
        DefaultExecutorSupplier.instance.forMainTasks().executeAfter({
            //navigation
            if (findNavController().currentDestination?.id == R.id.splashFragment) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment, null)
                // Reset the value to prevent re-navigation
            }
        }, 2000)
    }

    override fun initObservers() {

    }
}