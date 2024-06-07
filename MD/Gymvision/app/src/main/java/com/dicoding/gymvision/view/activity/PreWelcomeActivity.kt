package com.dicoding.gymvision.view.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.dicoding.gymvision.R
import com.dicoding.gymvision.databinding.ActivityPreWelcomeBinding

class PreWelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemBars()
        initializeButtons()
    }

    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, WelcomeActivity::class.java))
    }

    private fun initializeButtons() {
        binding.fabArr.setOnClickListener { navigateToLogin() }
    }
}