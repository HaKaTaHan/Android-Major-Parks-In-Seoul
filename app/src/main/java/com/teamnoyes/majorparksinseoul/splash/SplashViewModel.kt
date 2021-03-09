package com.teamnoyes.majorparksinseoul.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.BuildConfig
import com.teamnoyes.majorparksinseoul.network.NetworkController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val _timer = MutableLiveData(false)
    val timer: LiveData<Boolean>
        get() = _timer

    private val _splashStatus = MutableLiveData<SplashStatus>()
    val splashStatus: LiveData<SplashStatus>
        get() = _splashStatus

    fun setTimer(){
        viewModelScope.launch {
            delay(1500L)
            _timer.value = true
        }
    }

    fun getParksData(){
        viewModelScope.launch {
            _splashStatus.value = SplashStatus.LOADING

            try {
                val data = NetworkController.seoulParkService.getAllParkData(1, 5)

                if (data.body()!!.SearchParkInfoService.RESULT.CODE == "INFO-000"){
                    //각 구별로 데이터 분류해야함
                    delay(1000L)

                    _splashStatus.value = SplashStatus.SUCCESS
                }
                else{
                    //인터셉터로 빼보자
                    _splashStatus.value = SplashStatus.CLIENT_ERROR
                    when(data.body()!!.SearchParkInfoService.RESULT.CODE){

                    }
                }
                println(data)
            }
            catch (e: Exception){
                _splashStatus.value = SplashStatus.SERVER_ERROR
                e.printStackTrace()
            }

        }
    }
}