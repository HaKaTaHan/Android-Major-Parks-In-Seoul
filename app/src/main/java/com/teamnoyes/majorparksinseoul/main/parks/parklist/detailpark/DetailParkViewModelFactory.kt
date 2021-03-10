package com.teamnoyes.majorparksinseoul.main.parks.parklist.detailpark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailParkViewModelFactory(private val regionName: String, private val pIdx: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailParkViewModel::class.java))
            return DetailParkViewModel(regionName, pIdx) as T
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}