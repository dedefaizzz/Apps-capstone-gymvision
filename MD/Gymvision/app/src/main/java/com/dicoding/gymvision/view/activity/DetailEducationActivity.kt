package com.dicoding.gymvision.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.gymvision.data.model.Education
import com.dicoding.gymvision.databinding.ActivityDetailEducationBinding

class DetailEducationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataEdu = intent.getParcelableExtra<Education>(EXTRA_DATA_EDUCATION)

        if (dataEdu != null) {
            binding.titleTextView.text = dataEdu.name
            binding.descriptionTextView.text = dataEdu.description
            Glide.with(this).load(dataEdu.photo).into(binding.imageViewEdu)
        }
    }

    companion object {
        const val EXTRA_DATA_EDUCATION = "extra_data_education"
    }
}
