package com.nekobitlz.aviasales.features.direction

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nekobitlz.aviasales.R
import com.nekobitlz.aviasales.di.injector
import com.nekobitlz.aviasales.features.direction.di.DirectionComponent
import com.nekobitlz.aviasales.features.map.MapFragment
import com.nekobitlz.aviasales.features.search.SearchFragment
import com.nekobitlz.aviasales.router.Router
import com.nekobitlz.aviasales.router.command.DirectionCommand
import com.nekobitlz.aviasales.router.command.ErrorCommand
import com.nekobitlz.aviasales.router.command.MapCommand
import kotlinx.android.synthetic.main.fragment_direction.*

class DirectionFragment : Fragment(), DirectionComponent by injector.directionModule {

    private lateinit var viewModel: DirectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_direction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        initViews()
    }

    override fun onDetach() {
        onCitySelectedListener = null
        super.onDetach()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, directionViewModelFactory).get(DirectionViewModel::class.java)

        viewModel.getDirectionFrom().observe(viewLifecycleOwner, Observer {
            tv_direction_from.text = it.cityName
            tv_direction_from_city_code.text = it.getCityCode()
        })
        viewModel.getDirectionTo().observe(viewLifecycleOwner, Observer {
            tv_direction_to.text = it.cityName
            tv_direction_to_city_code.text = it.getCityCode()
        })
        viewModel.getRouter().observe(viewLifecycleOwner, Observer {
            when (it.peekContent()) {
                is MapCommand -> it.getContentIfNotHandled()?.perform(MapFragment(), Router)
                is DirectionCommand -> it.getContentIfNotHandled()?.perform(SearchFragment(), Router)
                is ErrorCommand -> it.getContentIfNotHandled()?.perform(this, Router)
            }
        })

        onCitySelectedListener = viewModel
    }

    private fun initViews() {
        tv_direction_from.setOnClickListener {
            viewModel.onDirectionFromClicked()
        }

        tv_direction_to.setOnClickListener {
            viewModel.onDirectionToClicked()
        }

        btn_search.setOnClickListener {
            viewModel.onSearchClicked()
        }

        ib_swap.setOnClickListener {
            animateSwap()
        }
    }

    private fun animateSwap() {
        val anim1 = createObjectAnimator(tv_direction_from, 1.0f, 0.0f)
        val anim2 = createObjectAnimator(tv_direction_to, 1.0f, 0.0f)
        val anim3 = createObjectAnimator(tv_direction_from_city_code, 1.0f, 0.0f)
        val anim4 = createObjectAnimator(tv_direction_to_city_code, 1.0f, 0.0f)

        val anim5 = createObjectAnimator(tv_direction_from, 0.0f, 1.0f)
        val anim6 = createObjectAnimator(tv_direction_to, 0.0f, 1.0f)
        val anim7 = createObjectAnimator(tv_direction_from_city_code, 0.0f, 1.0f)
        val anim8 = createObjectAnimator(tv_direction_to_city_code, 0.0f, 1.0f)

        AnimatorSet().apply {
            playTogether(anim1, anim2, anim3, anim4)
            start()
            doOnEnd {
                viewModel.onSwapClicked()

                playTogether(anim5, anim6, anim7, anim8)
                start()
            }
        }
    }

    private fun createObjectAnimator(
        view: View,
        fromValue: Float,
        toValue: Float
    ): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, "alpha", fromValue, toValue).apply {
            duration = 300
        }
    }
}