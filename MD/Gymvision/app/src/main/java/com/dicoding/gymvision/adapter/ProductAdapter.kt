package com.dicoding.gymvision.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.gymvision.R
import com.dicoding.gymvision.data.model.Product
import com.dicoding.gymvision.view.activity.DetailSuplemenActivity

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvProductName)
        val productDescription: TextView = itemView.findViewById(R.id.tvProductDescription)
        val productImage: ImageView = itemView.findViewById(R.id.imageViewProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_suplemen, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name

        // Display only a shortened version of the description
        val shortDescription = if (product.description.length > 100) {
            "${product.description.substring(0, 100)}..."
        } else {
            product.description
        }
        holder.productDescription.text = shortDescription

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .placeholder(R.drawable.ic_place_holder) // Placeholder image
            .into(holder.productImage)

        holder.itemView.setOnClickListener {
            // Menampilkan detail produk
            val context = holder.itemView.context
            val intent = Intent(context, DetailSuplemenActivity::class.java).apply {
                putExtra("name", product.name)
                putExtra("description", product.description)
                putExtra("imageUrl", product.imageUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = productList.size
}
