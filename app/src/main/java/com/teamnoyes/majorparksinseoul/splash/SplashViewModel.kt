package com.teamnoyes.majorparksinseoul.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val _timer = MutableLiveData(false)
    val timer: LiveData<Boolean>
        get() = _timer

    fun setTimer(){
        viewModelScope.launch {
            delay(1500L)
            _timer.value = true
        }
    }
}