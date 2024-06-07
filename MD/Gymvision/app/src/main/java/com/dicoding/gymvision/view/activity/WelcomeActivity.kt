package com.dicoding.gymvision.view.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.dicoding.gymvision.R
import com.dicoding.gymvision.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemBars()
        initializeButtons()
//        startAnimations()
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

    private fun initializeButtons() {
        binding.btnLogin.setOnClickListener { navigateToLogin() }
        binding.btnSignup.setOnClickListener { navigateToRegister() }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

//    private fun startAnimations() {
//        val imageViewAnim = ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
//            duration = 6000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }
//
//        val fadeInDuration = 100L
//        val titleAnimation = ObjectAnimator.ofFloat(binding.tvTittle, View.ALPHA, 1f).setDuration(fadeInDuration)
//        val descAnimation = ObjectAnimator.ofFloat(binding.tvDesc, View.ALPHA, 1f).setDuration(fadeInDuration)
//        val loginAnimation = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(fadeInDuration)
//        val signupAnimation = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(fadeInDuration)
//
//        val buttonAnimations = AnimatorSet().apply {
//            playTogether(loginAnimation, signupAnimation)
//        }
//
//        AnimatorSet().apply {
//            playSequentially(titleAnimation, descAnimation, buttonAnimations)
//            start()
//        }
//
//        imageViewAnim.start()
//    }
}