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

        val productList = listOf(
            Product(name = "Product 1", description = "Description for Product 1", imageUrl = "https://example.com/image1.jpg"),
            Product(name = "Product 2", description = "Description for Product 2", imageUrl = "https://example.com/image2.jpg"),
            // Tambahkan produk lainnya
        )

        val adapter = ProductAdapter(productList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}
