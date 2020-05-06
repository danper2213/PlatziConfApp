package com.danpern.platziconf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.danpern.platziconf.R


import com.danpern.platziconf.model.Ubication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.io.Console

/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication = Ubication()

        val zoom = 16f
        val centerMap = LatLng(ubication.latitud, ubication.longitud)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark = LatLng(ubication.latitud, ubication.longitud)
        val markerOption = MarkerOptions()
        markerOption.position(centerMark)
        markerOption.title("Platzi Conf")

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150, 150, false)
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        googleMap?.addMarker(markerOption)
        googleMap?.setOnMarkerClickListener(this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }

}
