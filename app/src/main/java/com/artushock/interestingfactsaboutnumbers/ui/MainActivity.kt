package com.artushock.interestingfactsaboutnumbers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artushock.interestingfactsaboutnumbers.R
import com.artushock.interestingfactsaboutnumbers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = MainFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment)
            .commit()
    }
}