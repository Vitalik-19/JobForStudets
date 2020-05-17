package com.example.jobforstudent.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.jobforstudent.R
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.ui.search.SearchAdapter.SearchViewHolder

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    var data = listOf<Advert>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameWorkText: TextView = itemView.findViewById(R.id.item_advert_work_name_text)
        var companyNameText: TextView = itemView.findViewById(R.id.item_advert_company_name_text)
        var locationText: TextView = itemView.findViewById(R.id.item_advert_location_text)
        var salary: TextView = itemView.findViewById(R.id.item_advert_salary_text)
        val favoriteImage: ImageView = itemView.findViewById(R.id.item_advert_favorite_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            nameWorkText.text = data[position].workName
            companyNameText.text = data[position].companyName
            locationText.text = data[position].location
            salary.text = data[position].salary.toString()
            favoriteImage.setOnClickListener {
                favoriteImage.setImageResource(R.drawable.ic_favorite_true_black_24dp)
            }
            itemView.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_search_to_workFragment))
        }
    }
}