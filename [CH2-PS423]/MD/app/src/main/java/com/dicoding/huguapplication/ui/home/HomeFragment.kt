package com.dicoding.huguapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.huguapplication.R
import com.dicoding.huguapplication.data.Majalah
import com.dicoding.huguapplication.ui.kuesioner.KuesionerActivity

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find your button by its ID from the inflated view
        val MajalahButton: Button = view.findViewById(R.id.btn_majalah)

        // Set an OnClickListener for the button
        MajalahButton.setOnClickListener {
            val intent = Intent(requireContext(), MajalahActivity::class.java)
            startActivity(intent)
        }

        val KuesionerButton: Button = view.findViewById(R.id.btn_kuesioner)

        // Set an OnClickListener for the button
        KuesionerButton.setOnClickListener {
            val intent = Intent(requireContext(), KuesionerActivity::class.java)
            startActivity(intent)
        }
    }
}