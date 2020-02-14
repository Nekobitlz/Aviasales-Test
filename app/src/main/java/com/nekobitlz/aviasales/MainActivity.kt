package com.nekobitlz.aviasales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nekobitlz.aviasales.features.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, SearchFragment.newInstance(), null)
                .commit()
        }
    }
}
