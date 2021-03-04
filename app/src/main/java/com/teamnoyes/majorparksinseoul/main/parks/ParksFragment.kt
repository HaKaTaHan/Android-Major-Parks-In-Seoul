package com.teamnoyes.majorparksinseoul.main.parks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.ParksFragmentBinding

class ParksFragment : Fragment() {
    private lateinit var parksFragmentBinding: ParksFragmentBinding
    private lateinit var parksViewModel: ParksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parksFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.parks_fragment, container, false)
        return parksFragmentBinding.root
    }


}