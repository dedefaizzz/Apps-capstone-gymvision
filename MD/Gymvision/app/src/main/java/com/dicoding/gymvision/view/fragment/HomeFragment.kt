package com.dicoding.gymvision.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.dicoding.gymvision.R
import com.dicoding.gymvision.adapter.EducationAdapter
import com.dicoding.gymvision.data.model.Education
import com.dicoding.gymvision.view.activity.WelcomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var educationAdapter: EducationAdapter
    private val listEducation = ArrayList<Education>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize the floating action button
        val fabLogout = view.findViewById<FloatingActionButton>(R.id.fab_logout)
        fabLogout.setOnClickListener {
            // Perform logout action here
            logout()
        }

        // Setup ImageSlider
        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        // Add images from drawable without title
        imageList.add(SlideModel(R.drawable.image_3))
        imageList.add(SlideModel(R.drawable.image_2))
        imageList.add(SlideModel(R.drawable.image_1))
        imageList.add(SlideModel(R.drawable.image_4))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        // Setup RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewEducation)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        educationAdapter = EducationAdapter(listEducation)
        recyclerView.adapter = educationAdapter

        populateEducationData()

        return view
    }

    private fun populateEducationData() {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        for (i in dataName.indices) {
            val edu = Education(dataName[i], dataDescription[i], dataPhoto[i])
            listEducation.add(edu)
        }
        educationAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        // Hide the action bar when the fragment is visible
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onPause() {
        super.onPause()
        // Show the action bar when the fragment is not visible
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun logout() {
        val intent = Intent(activity, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

