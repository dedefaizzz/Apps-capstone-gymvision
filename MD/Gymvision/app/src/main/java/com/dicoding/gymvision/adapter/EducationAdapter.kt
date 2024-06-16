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
import com.dicoding.gymvision.data.model.Education
import com.dicoding.gymvision.view.activity.DetailEducationActivity

class EducationAdapter(private val listEducation: ArrayList<Education>) : RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    inner class EducationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val previewImageView: ImageView = itemView.findViewById(R.id.iv_preview)
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return EducationViewHolder(view)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val education = listEducation[position]
        holder.usernameTextView.text = education.name
        Glide.with(holder.previewImageView.context)
            .load(education.photo)
            .placeholder(R.drawable.ic_place_holder)
            .into(holder.previewImageView)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailEducationActivity::class.java).apply {
                putExtra(DetailEducationActivity.EXTRA_DATA_EDUCATION, education)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listEducation.size
}
