package com.teamnoyes.majorparksinseoul.main.parks.parklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.ParklistFragmentBinding

class ParklistFragment : Fragment() {
    private lateinit var parklistFragmentBinding: ParklistFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parklistFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.parklist_fragment, container, false)

        println(arguments?.getString("regionName"))

        return parklistFragmentBinding.root
    }

}