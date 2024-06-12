package com.dicoding.gymvision.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.dicoding.gymvision.R
import com.dicoding.gymvision.view.activity.WelcomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
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

        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://i.pinimg.com/564x/d1/ef/db/d1efdba923702381cf8229aae885c579.jpg","GymVision"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/63/24/bb/6324bb0595080157562823c8ab87ea34.jpg","GymVision"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/04/0a/9f/040a9ff96a0c6490c19cd0753a7a4ea4.jpg","GymVision"))
        imageList.add(SlideModel("https://i.pinimg.com/564x/c5/e0/a2/c5e0a2ca09cfa0253114eee835f80428.jpg","GymVision"))

        imageSlider.setImageList(imageList,ScaleTypes.FIT)

        return view
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
