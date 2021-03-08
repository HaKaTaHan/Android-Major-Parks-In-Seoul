package com.teamnoyes.majorparksinseoul.main.parks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamnoyes.majorparksinseoul.datamodel.ModelParks
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class ParksViewModel(application: Application) : AndroidViewModel(application) {
    private val _loadRegion = MutableLiveData<List<ModelParks>>()
    val loadRegion: LiveData<List<ModelParks>>
        get() = _loadRegion
    private val app = application

    fun getRegion(){
        viewModelScope.launch {
            try {
                val inputStream = app.assets.open("json/Region.json")
                val json = inputStream.bufferedReader().use {
                    it.readText()
                }

                val regionJson = JSONObject(json)
                println("-------------------")
                val list = mutableListOf<ModelParks>()
                for ((idx, key) in regionJson.keys().withIndex()){
                    println(regionJson[key])
                    list.add(ModelParks(regionJson[key].toString(), idx))
                }

                _loadRegion.value = list
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
}