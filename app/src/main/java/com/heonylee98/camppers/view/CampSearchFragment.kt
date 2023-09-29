package com.heonylee98.camppers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.heonylee98.camppers.MainActivity
import com.heonylee98.camppers.R
import com.heonylee98.camppers.databinding.FragmentCampSearchBinding
import com.opencsv.CSVParser
import com.opencsv.CSVReader
import java.io.InputStreamReader
import java.nio.file.Files
import kotlin.io.path.Path

class CampSearchFragment : Fragment(),OnMapReadyCallback {
    lateinit var fragmentCampSearchBinding: FragmentCampSearchBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCampSearchBinding = FragmentCampSearchBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentCampSearchBinding.run {
            // val rootView = inflater.inflate(R.layout.fragment_camp_search, container, false)
            campMapView.run {
                onCreate(savedInstanceState)
                getMapAsync(this@CampSearchFragment)
            }
        }
        // getCampData()
        // tools - sdk tools - google play service sdk download

        // 필터 태그를 통해 분류된 데이터를 받아와 지도에 핀 표시
        // 도 단위 지역을 고르기 전까진 데이터 로딩 X
        // 핀을 클릭하면 bottom sheet를 통해 해당 시설 정보 띄우기

        // bottom sheet 에서 가상의 캠핑장 주인과 1:1대화를 바로 연결하는 버튼 1
        // bottom sheet 에서 가상의 캠핑장 예약 기능을 바로 연결하는 버튼 2

        return fragmentCampSearchBinding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val point = LatLng( 37.5283169, 126.9294254)
        googleMap.addMarker(MarkerOptions().position(point).title("현위치"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,12f))
    }

    // camping_data.csv 파일 읽기 메서드
    private fun getCampData() {
        val assetManager = mainActivity.assets
        val inputStream = assetManager.open("camping_data.csv")

        val reader = CSVReader(InputStreamReader(inputStream))

        val allContents = reader.readAll()
        for (i in allContents) {
            Log.d("!!", i.get(0).toString())

            // 필수 항목 열 번호
            // 3,4,14,16,17,19~28,32~36,38,40,41,45,46

            // 글램핑 필요 열 번호
            // 47~54
        }
    }

    override fun onStart() {
        super.onStart()
        fragmentCampSearchBinding.campMapView.onStart()
    }
    override fun onStop() {
        super.onStop()
        fragmentCampSearchBinding.campMapView.onStop()
    }
    override fun onResume() {
        super.onResume()
        fragmentCampSearchBinding.campMapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        fragmentCampSearchBinding.campMapView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        fragmentCampSearchBinding.campMapView.onLowMemory()
    }
    override fun onDestroy() {
        super.onDestroy()
        fragmentCampSearchBinding.campMapView.onDestroy()
    }
}