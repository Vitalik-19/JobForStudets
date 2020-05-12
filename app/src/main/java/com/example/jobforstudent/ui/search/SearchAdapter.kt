package com.example.jobforstudent.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobforstudent.CompanyData
import com.example.jobforstudent.R
import com.example.jobforstudent.ui.search.SearchAdapter.SearchViewHolder

class SearchAdapter(private val data: CompanyData) : RecyclerView.Adapter<SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameText: TextView = itemView.findViewById(R.id.item_advert_company_name_text)
        var locationText: TextView = itemView.findViewById(R.id.item_advert_location_text)
        val favoriteImage: ImageView = itemView.findViewById(R.id.item_advert_favorite_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun getItemCount() = data.name.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.nameText.text = data.name[position]
        holder.locationText.text = data.location[position]
        holder.favoriteImage.setOnClickListener {
            holder.favoriteImage.setImageResource(R.drawable.ic_favorite_true_black_24dp)
        }
    }
}