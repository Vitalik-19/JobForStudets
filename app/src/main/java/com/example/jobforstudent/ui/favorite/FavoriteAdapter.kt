package com.example.jobforstudent.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobforstudent.CompanyData
import com.example.jobforstudent.R
import com.example.jobforstudent.ui.favorite.FavoriteAdapter.FavoriteViewHolder

class FavoriteAdapter(private val data: CompanyData) : RecyclerView.Adapter<FavoriteViewHolder>() {

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameText: TextView = itemView.findViewById(R.id.item_advert_company_name_text)
        var locationText: TextView = itemView.findViewById(R.id.item_advert_location_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun getItemCount() = data.name.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.nameText.text = data.name[position]
        holder.locationText.text = data.location[position]
    }
}