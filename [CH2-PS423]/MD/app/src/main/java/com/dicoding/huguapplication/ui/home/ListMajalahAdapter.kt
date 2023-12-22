package com.dicoding.huguapplication.ui.home

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.huguapplication.R
import com.dicoding.huguapplication.data.Majalah

class ListMajalahAdapter(private val listAnime: ArrayList<Majalah>) : RecyclerView.Adapter<ListMajalahAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_magazine, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listAnime.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, poster, description) = listAnime[position]

        Glide.with(holder.itemView.context)
            .load(poster)
            .into(holder.imgPoster)

        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailMajalahActivity::class.java)
            intentDetail.putExtra("Anime", listAnime[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }

    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.img_item_poster)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}