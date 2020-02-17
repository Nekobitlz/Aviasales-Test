package com.nekobitlz.aviasales.router

import androidx.fragment.app.Fragment
import com.nekobitlz.aviasales.MainActivity
import com.nekobitlz.aviasales.R

interface IRouter {
    fun openFragment(fragment: Fragment)
    fun replaceFragment(fragment: Fragment)
    fun closeCurrentFragment()
}

object Router : IRouter {

    private var activity: MainActivity? = null

    override fun openFragment(fragment: Fragment) {
        activity?.let {
            it.supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_up, 0, 0, R.anim.slide_out_down)
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun replaceFragment(fragment: Fragment) {
        activity?.let {
            it.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

    override fun closeCurrentFragment() {
        activity?.let {
            it.supportFragmentManager.popBackStack()
        }
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }

    fun detachActivity() {
        this.activity = null
    }
}