package com.dicoding.huguapplication.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.huguapplication.R
import com.dicoding.huguapplication.data.Majalah

class MajalahActivity : AppCompatActivity() {
    private lateinit var rv_magazine : RecyclerView
    private val list = ArrayList<Majalah>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_majalah)

        rv_magazine = findViewById(R.id.rv_magazine)
        rv_magazine.setHasFixedSize(true)

        list.addAll(getListMajalah())
        showRecyclerList()
    }

    private fun getListMajalah(): ArrayList<Majalah> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPoster = resources.obtainTypedArray(R.array.data_poster)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listMajalah = ArrayList<Majalah>()
        for (i in dataName.indices) {
            val anime = Majalah(dataName[i], dataPoster.getResourceId(i,-1), dataDescription[i])
            listMajalah.add(anime)
        }
        return listMajalah
    }

    private fun showRecyclerList() {
        rv_magazine.layoutManager = LinearLayoutManager(this)
        val listMajalahAdapter = ListMajalahAdapter(list)
        rv_magazine.adapter = listMajalahAdapter

    }

}