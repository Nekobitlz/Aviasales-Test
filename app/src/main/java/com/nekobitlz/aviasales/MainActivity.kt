package com.nekobitlz.aviasales

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.nekobitlz.aviasales.features.direction.DirectionFragment
import com.nekobitlz.aviasales.router.Router
import com.nekobitlz.aviasales.router.command.ActivityCommand


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Router.setActivity(this)

        if (savedInstanceState == null) {
            ActivityCommand().perform(DirectionFragment(), Router)
        }
    }

    override fun onDestroy() {
        Router.detachActivity()
        super.onDestroy()
    }

    fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus

        if (view == null) {
            view = View(this)
        }

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
