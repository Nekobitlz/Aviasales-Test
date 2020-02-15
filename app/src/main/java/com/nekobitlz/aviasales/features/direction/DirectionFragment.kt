package com.nekobitlz.aviasales.features.direction

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.addListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nekobitlz.aviasales.R
import kotlinx.android.synthetic.main.fragment_direction.*

class DirectionFragment : Fragment() {

    private val viewModel: DirectionViewModel by activityViewModels()

    companion object {
        fun newInstance(): DirectionFragment =
            DirectionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_direction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tv_direction_from.text = "London"
        tv_direction_from_short.text = "LON"
        tv_direction_to.text = "Paris"
        tv_direction_to_short.text = "PAR"

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
            viewModel.onSwapClicked()
            initSwapAnimation()
        }
    }

    private fun initSwapAnimation() {
        val directionFrom = tv_direction_from.text
        val directionFromShort = tv_direction_from_short.text

        val anim1 = createObjectAnimator(tv_direction_from, 1.0f, 0.0f) {
            tv_direction_from.text = tv_direction_to.text
            createObjectAnimator(tv_direction_from, 0.0f, 1.0f).apply { start() }
        }

        val anim2 = createObjectAnimator(tv_direction_to, 1.0f, 0.0f) {
            tv_direction_to.text = directionFrom
            createObjectAnimator(tv_direction_to, 0.0f, 1.0f).apply { start() }
        }

        val anim3 = createObjectAnimator(tv_direction_from_short, 1.0f, 0.0f) {
            tv_direction_from_short.text = tv_direction_to_short.text
            createObjectAnimator(tv_direction_from_short, 0.0f, 1.0f).apply { start() }
        }

        val anim4 = createObjectAnimator(tv_direction_to_short, 1.0f, 0.0f) {
            tv_direction_to_short.text = directionFromShort
            createObjectAnimator(tv_direction_to_short, 0.0f, 1.0f).apply { start() }
        }

        AnimatorSet().apply {
            playTogether(anim1, anim2, anim3, anim4)
            start()
        }
    }

    private fun createObjectAnimator(view: View, fromValue: Float, toValue: Float, listener: (Animator) -> Unit = {}): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, "alpha", fromValue, toValue).apply {
            duration = 300
            addListener(listener)
        }
    }
}