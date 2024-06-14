package com.dicoding.gymvision.view.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.gymvision.R
import com.dicoding.gymvision.data.model.Education
import com.dicoding.gymvision.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val list = ArrayList<Education>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataEdu = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA_EDUCATION, Education::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA_EDUCATION)
        }

        if (dataEdu != null) {
            binding.titleTextView.text = dataEdu.name
            binding.descriptionTextView.text = dataEdu.description
            Glide.with(this).load(dataEdu.photo).into(binding.imageViewEdu)
        }

        list.addAll(getListEdu())
    }

    private fun getListEdu(): ArrayList<Education> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listEdu = ArrayList<Education>()
        for (i in dataName.indices) {
            val edu = Education(dataName[i], dataDescription[i], dataPhoto[i])
            listEdu.add(edu)
        }
        return listEdu
    }

    companion object {
        const val EXTRA_DATA_EDUCATION = "extra_data_education"
    }
}