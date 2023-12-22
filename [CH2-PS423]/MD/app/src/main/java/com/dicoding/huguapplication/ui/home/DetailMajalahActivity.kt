package com.dicoding.huguapplication.ui.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dicoding.huguapplication.R
import com.dicoding.huguapplication.data.Majalah

class DetailMajalahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_majalah)

        val actionbar = supportActionBar

        actionbar!!.title = "Detail Majalah"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val dataMajalah = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Majalah>("Majalah", Majalah::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Majalah>("Majalah")
        }

        val tvDetailName : TextView = findViewById(R.id.tv_item_name)
        val ivDetailPoster : ImageView = findViewById(R.id.img_item_poster)
        val tvDetailDescription: TextView = findViewById(R.id.tv_item_description)

        tvDetailName.text = dataMajalah?.name
        Glide.with(this)
            .load(dataMajalah?.poster)
            .into(ivDetailPoster)
        tvDetailDescription.text = dataMajalah?.description
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}