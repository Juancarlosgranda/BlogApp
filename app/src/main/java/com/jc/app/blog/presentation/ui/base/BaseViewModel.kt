package com.jc.app.blog.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jc.app.blog.R
import com.jc.app.blog.domain.utils.Failure
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val _spinner = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean> get() = _spinner
    val showErrorCause = MutableSharedFlow<Boolean>()

    lateinit var errorCause : Any

    protected fun handleUseCaseFailureFromBase(failure: Failure) {
        _spinner.value = false
        when(failure) {
            is Failure.UnauthorizedOrForbidden -> setError(failure.message)
            is Failure.None -> setError(R.string.error_failure_none)
            is Failure.NetworkConnectionLostSuddenly -> setError(R.string.error_failure_network_connection_lost_suddenly)
            is Failure.NoNetworkDetected -> setError(R.string.error_failure_no_network_detected)
            is Failure.SSLError -> setError(R.string.error_failure_ssl)
            is Failure.TimeOut -> setError(R.string.error_failure_time_out)
            is Failure.ServerBodyError -> setError(failure.message)
            is Failure.DataToDomainMapperFailure -> setError(R.string.error_general)
            is Failure.ServiceUncaughtFailure -> setError(R.string.error_failure_uncaught)
            else -> {}
        }
    }

    protected fun setError(cause: Any?) {
        errorCause = cause?:""
        viewModelScope.launch {
            showErrorCause.emit(true)
        }
    }

}