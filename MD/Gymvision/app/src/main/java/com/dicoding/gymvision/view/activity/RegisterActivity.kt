package com.dicoding.gymvision.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.gymvision.customview.EmailEditText
import com.dicoding.gymvision.customview.MyButton
import com.dicoding.gymvision.customview.PasswordEditText
import com.dicoding.gymvision.data.result.ResultState
import com.dicoding.gymvision.databinding.ActivityRegisterBinding
import com.dicoding.gymvision.view.viewmodel.MainViewModel
import com.dicoding.gymvision.view.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var emailField: EmailEditText
    private lateinit var passwordField: PasswordEditText
    private lateinit var registerButton: MyButton
    private lateinit var loginTextView: TextView
    private val viewModel: MainViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
        hideSystemUI()
//        playAnimations()
    }

    private fun initializeViews() {
        emailField = binding.emailEditText
        passwordField = binding.passwordEditText
        registerButton = binding.btnSignup
        loginTextView = binding.tvLogin

        emailField.addTextChangedListener(createTextWatcher())
        passwordField.addTextChangedListener(createTextWatcher())

        registerButton.setOnClickListener {
            if (registerButton.isEnabled) performRegistration()
        }

        loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun showRegistrationSuccessDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Registration Successful!")
            setMessage("Account created successfully. Please log in.")
            setPositiveButton("Continue") { _, _ ->
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }

//    private fun playAnimations() {
//        val moveAnim = ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
//            duration = 6000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }
//        moveAnim.start()
//
//        val fadeInDuration = 100L
//        val animators = listOf(
//            ObjectAnimator.ofFloat(binding.tvTittle, View.ALPHA, 0f, 1f).setDuration(fadeInDuration),
//            ObjectAnimator.ofFloat(binding.tvName, View.ALPHA, 0f, 1f).setDuration(fadeInDuration),
//            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 0f, 1f).setDuration(fadeInDuration),
//            ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 0f, 1f).setDuration(fadeInDuration),
//            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 0f, 1f).setDuration(fadeInDuration),
//            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 0f, 1f).setDuration(fadeInDuration),
//            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 0f, 1f).setDuration(fadeInDuration),
//            ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 0f, 1f).setDuration(fadeInDuration)
//        )
//
//        AnimatorSet().apply {
//            playSequentially(animators)
//            startDelay = 100L
//        }.start()
//    }

    private fun performRegistration() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        viewModel.userRegister(name, email, password).observe(this) { result ->
            when (result) {
                is ResultState.Loading -> showProgressBar(true)
                is ResultState.Success -> {
                    val message = result.data ?: "Registration successful"
                    showToast(message)
                    showProgressBar(false)
                    showRegistrationSuccessDialog()
                }
                is ResultState.Error -> {
                    val errorMessage = result.exception.message ?: "An error occurred"
                    showToast(errorMessage)
                    showProgressBar(false)
                }
            }
        }
    }

    private fun createTextWatcher() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            updateRegisterButtonState()
        }
        override fun afterTextChanged(s: Editable) {}
    }

    private fun updateRegisterButtonState() {
        val isEmailValid = emailField.text.toString().isNotEmpty() && emailField.error == null
        val isPasswordValid = passwordField.text.toString().isNotEmpty() && passwordField.error == null

        registerButton.isEnabled = isEmailValid && isPasswordValid
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
