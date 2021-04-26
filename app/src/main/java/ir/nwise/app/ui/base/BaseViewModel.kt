package ir.nwise.app.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VS> : ViewModel() {
    val liveData = MutableLiveData<VS>()
}