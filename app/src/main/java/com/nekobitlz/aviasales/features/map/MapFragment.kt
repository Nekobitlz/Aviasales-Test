package com.nekobitlz.aviasales.features.map

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.maps.android.SphericalUtil
import com.nekobitlz.aviasales.R
import com.nekobitlz.aviasales.data.models.City
import com.nekobitlz.aviasales.di.injector
import com.nekobitlz.aviasales.features.map.di.MapComponent
import com.nekobitlz.aviasales.utils.PlaneTypeEvaluator

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var marker: Marker
    private lateinit var map: GoogleMap
    private lateinit var animator: ObjectAnimator

    private lateinit var component: MapComponent
    private lateinit var viewModel: MapViewModel

    private val mapViewModelFactory: MapViewModelFactory by lazy {
        component.mapViewModelFactory
    }

    private var currentAnimationTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments?.getParcelable<MapArguments>(ARGUMENTS_KEY)!!
        component = injector.getMapModule(args)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMap(savedInstanceState)
        initViewModel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(CURRENT_ANIMATION_TIME_KEY, animator.currentPlayTime)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        animator.cancel()
        super.onDestroyView()
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map

        viewModel.getAnimationEvent().observe(viewLifecycleOwner, Observer {
            startAnimation(it.peekContent())
        })

        viewModel.onMapReady()
    }

    fun setAnimatedPlane(latLng: LatLng) {
        marker.position = latLng
    }

    private fun initMap(savedInstanceState: Bundle?) {
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)

        if (savedInstanceState != null) {
            currentAnimationTime = savedInstanceState.getLong(CURRENT_ANIMATION_TIME_KEY)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, mapViewModelFactory).get(MapViewModel::class.java)
    }

    private fun startAnimation(direction: Pair<City, City>) {
        val cityFrom = direction.first.location.toLatLng()
        val cityTo = direction.second.location.toLatLng()

        moveCamera(cityFrom, cityTo)
        addMarkers(cityFrom, cityTo)
        addPolyline(cityFrom, cityTo)
        animatePlane(cityFrom, cityTo)
    }

    private fun moveCamera(cityFrom: LatLng, cityTo: LatLng) {
        val latLngBounds = LatLngBounds
            .builder()
            .include(cityFrom)
            .include(cityTo)
            .build()

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (width * PADDING_RATE).toInt()

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, width, height, padding))
    }

    private fun addMarkers(cityFrom: LatLng, cityTo: LatLng) {
        addPointMarker(cityFrom)
        addPointMarker(cityTo)
        addPlaneMarker(cityFrom, cityTo)
    }

    private fun addPointMarker(latLng: LatLng) {
        val marker = MarkerOptions()
            .alpha(MARKER_ALPHA)
            .icon(
                BitmapDescriptorFactory.defaultMarker()
                // TODO Make custom markers
            )
            .position(latLng)

        map.addMarker(marker)
    }

    private fun addPlaneMarker(cityFrom: LatLng, cityTo: LatLng) {
        marker = map.addMarker(
            MarkerOptions()
                .position(cityFrom)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plane))
                .rotation(
                    SphericalUtil.computeHeading(
                        cityFrom,
                        cityTo
                    ).toFloat() - PLANE_ICON_ANGLE_OFFSET
                )
                .zIndex(Z_INDEX)
                .anchor(ANCHOR, ANCHOR)
        )
    }

    private fun addPolyline(cityFrom: LatLng, cityTo: LatLng) {
        val polylinePattern = listOf(Dot(), Gap(DOT_GAP))
        val polyline = PolylineOptions().apply {
            add(cityFrom, cityTo)
            color(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
            geodesic(false)
            pattern(polylinePattern)
            width(DOT_GAP)
        }

        map.addPolyline(polyline)
    }

    private fun animatePlane(cityFrom: LatLng, cityTo: LatLng) {
        animator = ObjectAnimator
            .ofObject(this, ANIMATOR_PROPERTY_NAME, PlaneTypeEvaluator(), cityFrom, cityTo)
            .apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = ANIMATION_TIME
                currentPlayTime = currentAnimationTime
                start()
            }
    }

    companion object {
        private const val CURRENT_ANIMATION_TIME_KEY = "CURRENT_ANIMATION_TIME_KEY"
        private const val ARGUMENTS_KEY = "ARGUMENTS_KEY"

        private const val ANIMATOR_PROPERTY_NAME = "animatedPlane"
        private const val PLANE_ICON_ANGLE_OFFSET = 90f
        private const val PADDING_RATE = 0.15f
        private const val Z_INDEX = 1f
        private const val DOT_GAP = 7f
        private const val MARKER_ALPHA = 0.95f
        private const val ANCHOR = 0.5f
        private const val ANIMATION_TIME = 10000L

        fun createArgs(args: MapArguments): Bundle = Bundle(1).apply {
            putParcelable(ARGUMENTS_KEY, args)
        }
    }
}