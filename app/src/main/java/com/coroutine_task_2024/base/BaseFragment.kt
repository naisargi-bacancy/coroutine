package com.coroutine_task_2024.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {

    /**
     * Abstract method to provide layout resource id
     */
    protected abstract val layoutResourceId: Int

    /**
     * Return the binding instance to child fragment
     */
    protected fun getViewDataBinding(): B {
        return mBinding
    }

    /**
     * Abstract method to provide the ViewModel class
     */
    protected abstract val viewModelClass: Class<VM>

    /**
     * Abstract method to provide the binding variable
     */
    protected abstract val bindingVariable: Int

    /**
     * Return the binding instance to child fragment
     */
    protected fun getViewModel(): VM {
        return mViewModel
    }

    /**
     * Abstract method to setup view
     */
    protected abstract fun setUpViews()

    /**
     * Abstract method for observers
     */
    protected abstract fun initObservers()

    /**
     * -------------------------------------
     * internal use
     */
    private lateinit var mBinding: B
    private lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate binding
        mBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setUpViews()
        initObservers()
    }

    private fun setupViewModel() {
        //initiate viewmodel
        mViewModel = ViewModelProvider(this)[viewModelClass]
        if (bindingVariable != 0) {
            //set viewmodel variable in binding
            mBinding.setVariable(bindingVariable, mViewModel)
        }
        //set lifecycle owner to fragment for livedata updates
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.executePendingBindings()
    }

}