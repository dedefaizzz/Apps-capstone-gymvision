package com.dicoding.gymvision.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dicoding.gymvision.R

class DetailSuplemenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_suplemen)

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
    }
}