package com.example.test0.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * Created by ucm on 2018/3/21.
 */
open class BaseViewModel(context: Application) : AndroidViewModel(context) {

    val dataLoading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    val onErr = MutableLiveData<ErrorMsg>()


}