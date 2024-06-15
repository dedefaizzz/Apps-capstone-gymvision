package com.dicoding.gymvision.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.gymvision.R
import com.dicoding.gymvision.adapter.ProductAdapter
import com.dicoding.gymvision.data.model.Product

class SuplemenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suplemen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_suplemen)

        val productNames = resources.getStringArray(R.array.product_names)
        val productDescriptions = resources.getStringArray(R.array.product_descriptions)
        val productImageUrls = resources.getStringArray(R.array.product_image_urls)

        val productList = mutableListOf<Product>()
        for (i in productNames.indices) {
            productList.add(
                Product(
                    name = productNames[i],
                    description = productDescriptions[i],
                    imageUrl = productImageUrls[i]
                )
            )
        }

        val adapter = ProductAdapter(productList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


    }
}
