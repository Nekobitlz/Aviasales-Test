package com.nekobitlz.aviasales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}
