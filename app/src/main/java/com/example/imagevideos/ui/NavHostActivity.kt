package com.example.imagevideos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imagevideos.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }
}