package com.dicoding.gymvision.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
