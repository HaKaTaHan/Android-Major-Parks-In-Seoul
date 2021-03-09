package com.teamnoyes.majorparksinseoul.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.datamodel.ModelAllParkDataInfo
import com.teamnoyes.majorparksinseoul.network.NetworkController
import com.teamnoyes.majorparksinseoul.utils.ParksData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val START_INDEX = 1
    private val END_INDEX = 1000

    private val _splashStatus = MutableLiveData<SplashStatus>()
    val splashStatus: LiveData<SplashStatus>
        get() = _splashStatus

    fun getParksData(){
        viewModelScope.launch {
            _splashStatus.value = SplashStatus.LOADING

            try {
                val data = NetworkController.seoulParkService.getAllParkData(START_INDEX, END_INDEX)

                if (data.body()!!.SearchParkInfoService.RESULT.CODE == "INFO-000"){
                    //각 구별로 데이터 분류해야함
                    ParksData.setData(data.body()!!.SearchParkInfoService.row.sortedWith(compareBy{it.P_IDX}).groupBy { it.P_ZONE } as HashMap<String, List<ModelAllParkDataInfo>>)

                    delay(1000L)

                    _splashStatus.value = SplashStatus.SUCCESS
                }
                else{
                    //오류 코드를 인터셉터로 빼보자
                    _splashStatus.value = SplashStatus.CLIENT_ERROR

                }
            }
            catch (e: Exception){
                _splashStatus.value = SplashStatus.SERVER_ERROR
                e.printStackTrace()
            }

        }
    }
}