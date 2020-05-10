package com.example.jobforstudent.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobforstudent.R
import com.example.jobforstudent.ui.search.SearchAdapter.SearchViewHolder

class SearchAdapter(private val data: List<String>) : RecyclerView.Adapter<SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.item_advert_company_name_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

}