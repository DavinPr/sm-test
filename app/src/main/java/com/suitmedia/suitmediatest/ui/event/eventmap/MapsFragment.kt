package com.suitmedia.suitmediatest.ui.event.eventmap

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.suitmedia.suitmediatest.R
import com.suitmedia.suitmediatest.databinding.FragmentMapsBinding
import com.suitmedia.suitmediatest.model.Event
import com.suitmedia.suitmediatest.ui.event.EventViewModel
import com.suitmedia.suitmediatest.utils.toPresentation
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapsFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventViewModel by viewModel()

    private lateinit var mMap: GoogleMap
    private lateinit var eventListAdapter: EventListMapAdapter

    private val eventList = ArrayList<Event>()
    private val markerList = HashMap<Int, Marker>()
    var currentPos = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventListAdapter = EventListMapAdapter()

        lifecycleScope.launchWhenCreated {
            viewModel.getEvents.collect { list ->
                eventList.addAll(list.map { it.toPresentation() })
                eventListAdapter.setList(list.map { it.toPresentation() })
            }
        }

        binding.eventList.apply {
            adapter = eventListAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            }
            setPageTransformer(compositePageTransformer)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    onMarkerChanged(position, currentPos)
                }
            })
        }

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val builder = LatLngBounds.Builder()
        eventList.forEachIndexed { index, event ->
            val latLng = LatLng(event.lat, event.lng)
            val marker = mMap.addMarker(MarkerOptions().position(latLng))

            val bitmap = createDrawableFromView(
                requireContext(),
                getMarkerLayout(event.name, R.color.primaryColor)
            )
            if (bitmap != null) {
                marker?.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))
            }

            if (marker != null) {
                markerList[index] = marker
            }
            builder.include(latLng)
        }
        val bounds = builder.build()
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))

        mMap.setOnMarkerClickListener { marker ->
            val index = markerList.filterValues { it == marker }.keys.first()
            onMarkerChanged(index, currentPos)
            true
        }
    }

    @SuppressLint("InflateParams")
    private fun getMarkerLayout(title: String, color: Int): View {
        val marker: View =
            (context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.marker_layout,
                null
            )
        (marker.findViewById<View>(R.id.tv_marker_text) as TextView).apply {
            text = title
            backgroundTintList = ContextCompat.getColorStateList(requireContext(), color)
        }
        return marker
    }

    private fun createDrawableFromView(context: Context, view: View): Bitmap? {
        val displayMetrics = DisplayMetrics()

        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        view.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.buildDrawingCache()
        val bitmap =
            Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun onMarkerChanged(position: Int, currentPos: Int) {
        if (markerList[position] != null) {
            val bitmapActive = createDrawableFromView(
                requireContext(),
                getMarkerLayout(eventListAdapter.getList[position].name, R.color.green)
            )

            if (bitmapActive != null) {
                markerList[position]!!.setIcon(
                    BitmapDescriptorFactory.fromBitmap(
                        bitmapActive
                    )
                )
            }
            if (currentPos >= 0) {
                val bitmapInactive = createDrawableFromView(
                    requireContext(),
                    getMarkerLayout(
                        eventListAdapter.getList[currentPos].name,
                        R.color.primaryColor
                    )
                )
                if (bitmapInactive != null) {
                    markerList[currentPos]!!.setIcon(
                        BitmapDescriptorFactory.fromBitmap(
                            bitmapInactive
                        )
                    )
                }

            }
            mMap.animateCamera(CameraUpdateFactory.newLatLng(markerList[position]!!.position))
            this.currentPos = position
        }
    }

}