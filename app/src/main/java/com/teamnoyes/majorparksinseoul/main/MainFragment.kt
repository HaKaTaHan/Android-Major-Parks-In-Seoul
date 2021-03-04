package com.teamnoyes.majorparksinseoul.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var mainAdapter: MainAdapter
    private val tabTexts = arrayOf("지역별 공원", "즐겨찾기")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager(){
        mainAdapter = MainAdapter(this)
        mainBinding.pagerMain.adapter = mainAdapter
        TabLayoutMediator(mainBinding.tabsMain, mainBinding.pagerMain){tab, position ->
            tab.text = tabTexts[position]
        }.attach()
    }

}