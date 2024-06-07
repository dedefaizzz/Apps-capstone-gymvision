package com.dicoding.gymvision.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.dicoding.gymvision.R
import com.dicoding.gymvision.databinding.ActivityMainBinding
import com.dicoding.gymvision.view.viewmodel.MainViewModel
import com.dicoding.gymvision.view.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { ViewModelFactory.getInstance(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Contoh penggunaan binding untuk menampilkan konten
        viewModel.retrieveUserSession().observe(this) { user ->
            if (!user.isLogin) {
                navigateToPreWelcome()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                viewModel.userLogout()
                navigateToWelcome()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun navigateToPreWelcome() {
        val intent = Intent(this, PreWelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}