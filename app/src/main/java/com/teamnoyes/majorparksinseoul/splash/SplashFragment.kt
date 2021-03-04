package com.teamnoyes.majorparksinseoul.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.SplashFragmentBinding

class SplashFragment : Fragment() {
    private lateinit var splashFragmentBinding: SplashFragmentBinding
    private lateinit var splashViewModel: SplashViewModel
    private lateinit var splashViewModelFactory: SplashViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)

        return splashFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        splashViewModelFactory = SplashViewModelFactory(application)
        splashViewModel = ViewModelProvider(this, splashViewModelFactory).get(SplashViewModel::class.java)

        splashViewModel.timer.observe(viewLifecycleOwner, Observer {
            if (it){
                moveToMain()
            }
        })

        splashViewModel.setTimer()

    }

    private fun moveToMain(){
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
    }

}