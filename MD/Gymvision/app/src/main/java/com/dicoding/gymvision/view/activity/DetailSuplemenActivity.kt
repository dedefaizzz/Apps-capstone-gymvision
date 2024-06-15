package com.dicoding.gymvision.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.gymvision.R

class DetailSuplemenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_suplemen)

        // Make the status bar transparent initially
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT

        val productName: TextView = findViewById(R.id.tvProductDetailName)
        val productDescription: TextView = findViewById(R.id.tvProductDetailDescription)
        val productImage: ImageView = findViewById(R.id.imageViewProductDetail)

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("imageUrl")

        productName.text = name
        productDescription.text = description

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_place_holder) // Placeholder image
            .into(productImage)

        // Dynamically change the status bar color to match the CardView color
        window.statusBarColor = resources.getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
    }
}
