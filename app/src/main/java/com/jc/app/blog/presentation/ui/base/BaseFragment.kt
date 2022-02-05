package com.jc.app.blog.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.jc.app.blog.di.vm.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragmentWithViewModel<ViewDataBindingClass: ViewDataBinding, ViewModelType: BaseViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewDataBinding: ViewDataBindingClass
    lateinit var myViewModel: ViewModelType

    /**
     * This function associate the xml file to the class.
     * In order to pass it to onCreateView and bind it.
     * @return layout id
     */
    abstract val getLayoutId: Int

    /**
     * Return the binding variable of the XML
     * associated to this class.
     * @return binding variable ID
     */
    abstract val getBindingVariable: Int

    abstract fun getViewModel(): ViewModelType


    override fun onAttach(context: Context) {
        super.onAttach(context)
        myViewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        val rootView = viewDataBinding.root
        viewDataBinding.setVariable(getBindingVariable, myViewModel)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        onFragmentViewReady()
        onSetupListeners()
        viewDataBinding.executePendingBindings()
    }


    /**
     * We can override this function instead of [onViewCreated].
     * also this will be always listening to forceLogOut liveData.
     */
    open fun onFragmentViewReady() {

    }


    /**
     * override this for set up Listeners
     */
    open fun onSetupListeners() {

    }

    /**
     * this function for navigate between fragments
     */
    protected fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }

    protected fun goBack() {
        findNavController().popBackStack()
    }

}