package com.teamnoyes.majorparksinseoul.main.parks.parklist.detailpark

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.teamnoyes.majorparksinseoul.BuildConfig
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.DetailParkFragmentBinding

class DetailParkFragment : Fragment(), OnMapReadyCallback {
    private lateinit var detailParkFragmentBinding: DetailParkFragmentBinding
    private lateinit var detailParkViewModel: DetailParkViewModel
    private lateinit var detailParkViewModelFactory: DetailParkViewModelFactory

    private var regionName = ""
    private var pIdx = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        detailParkFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.detail_park_fragment, container, false)

        //regionName, pIdx 값 없으면 이거 오류처리 각
        regionName = arguments?.getString("regionName") ?: ""
        pIdx = arguments?.getInt("pIdx") ?: 0
        detailParkViewModelFactory = DetailParkViewModelFactory(regionName, pIdx)

        initToolbar()
        initNaverMap()

        return detailParkFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailParkViewModel = ViewModelProvider(this, detailParkViewModelFactory).get(DetailParkViewModel::class.java)
        detailParkFragmentBinding.detailParkViewModel = detailParkViewModel
        controlNaverMapVisible()

        detailParkViewModel.loadData()
    }

    private fun initToolbar(){
        detailParkFragmentBinding.toolbarDetailpark.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        detailParkFragmentBinding.toolbarDetailpark.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        //bookmark db에 있으면 like 아니면 default
        detailParkFragmentBinding.toolbarDetailpark.inflateMenu(R.menu.detailpark_default_item)
        detailParkFragmentBinding.toolbarDetailpark.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.detailPark_default -> {
                    detailParkFragmentBinding.toolbarDetailpark.menu.clear()
                    detailParkFragmentBinding.toolbarDetailpark.inflateMenu(R.menu.detailpark_like_item)
                    // db에 저장
                    true
                }
                R.id.detailPark_like -> {
                    detailParkFragmentBinding.toolbarDetailpark.menu.clear()
                    detailParkFragmentBinding.toolbarDetailpark.inflateMenu(R.menu.detailpark_default_item)
                    //db에서 제거
                    true
                }
                else -> false
            }
        }
    }

    private fun initNaverMap(){
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.naverMap) as MapFragment? ?:
        MapFragment.newInstance().also {
            fm.beginTransaction().add(R.id.naverMap, it).commit()
        }

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        moveToLocation(naverMap)
    }

    @UiThread
    private fun moveToLocation(naverMap: NaverMap){
        detailParkViewModel.pos.observe(viewLifecycleOwner, Observer {
            naverMap.cameraPosition = CameraPosition(LatLng(it.first, it.second), 12.0)

            val marker = Marker()
            marker.position = LatLng(it.first, it.second)
            marker.map = naverMap
        })
    }

    private fun controlNaverMapVisible(){
        detailParkViewModel.visible.observe(viewLifecycleOwner, Observer {
            if (it){
                detailParkFragmentBinding.cardDetailparkNavermap.visibility = View.GONE
            }
        })
    }

}