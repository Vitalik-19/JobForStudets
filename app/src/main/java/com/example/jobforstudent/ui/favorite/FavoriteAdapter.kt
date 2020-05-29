package com.example.jobforstudent.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jobforstudent.R
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.ui.favorite.FavoriteAdapter.FavoriteViewHolder

class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {
    var data = listOf<Advert>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val bundle = Bundle()

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameWorkText: TextView = itemView.findViewById(R.id.item_advert_work_name_text)
        var companyNameText: TextView = itemView.findViewById(R.id.item_advert_company_name_text)
        var locationText: TextView = itemView.findViewById(R.id.item_advert_location_text)
        var salary: TextView = itemView.findViewById(R.id.item_advert_salary_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.apply {
            nameWorkText.text = data[position].workName
            companyNameText.text = data[position].companyName
            locationText.text = data[position].location
            salary.text = data[position].salary.toString()
            itemView.setOnClickListener {
                bundle.putLong("advertId", data[position].advertId)
                it.findNavController().navigate(R.id.action_favoriteFragment_to_workFragment, bundle)
            }
        }
    }
}