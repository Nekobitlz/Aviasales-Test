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

    private lateinit var activity: MainActivity

    override fun openFragment(fragment: Fragment) {
        activity.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_up, 0, 0, R.anim.slide_out_down)
            .add(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun closeCurrentFragment() {
        activity.supportFragmentManager.popBackStack()
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }
}