package com.teamnoyes.majorparksinseoul.main.parks

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.teamnoyes.majorparksinseoul.datamodel.ModelParks

@BindingAdapter("parksName")
fun Button.setParksRegionName(item: ModelParks){
    text = item.name
}
