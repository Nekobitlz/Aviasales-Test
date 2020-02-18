package com.nekobitlz.aviasales.router

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nekobitlz.aviasales.MainActivity
import com.nekobitlz.aviasales.R

interface IRouter {
    fun openFragment(fragment: Fragment)
    fun replaceFragment(fragment: Fragment)
    fun closeCurrentFragment()
    fun showSnackBar(view: View, text: String)
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
            it.hideKeyboard()
        }
    }

    override fun showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }

    fun detachActivity() {
        this.activity = null
    }
}