package com.example.jobforstudent.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jobforstudent.R
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.ui.search.SearchAdapter.SearchViewHolder
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>(), Filterable {
    var data = ArrayList<Advert>()
    var workName = ""
    var location = ""
    var resultList = ArrayList<Advert>()

    var advertId: Long = 0L
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var dataFilterList = data
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        //dataFilterList = data
    }

    private val bundle = Bundle()

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

    override fun getItemCount() = dataFilterList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            nameWorkText.text = dataFilterList[position].workName
            companyNameText.text = dataFilterList[position].companyName
            locationText.text = dataFilterList[position].location
            salary.text = dataFilterList[position].salary.toString().let { "$it грн." }
            favoriteImage.setOnClickListener {
                favoriteImage.setImageResource(R.drawable.ic_favorite_true_black_24dp)
                advertId = dataFilterList[position].advertId
            }
            itemView.setOnClickListener {
                bundle.putLong("advertId", dataFilterList[position].advertId)
                it.findNavController().navigate(R.id.action_searchFragment_to_workFragment, bundle)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (workName == "" && location == "") {
                    dataFilterList = data
                } else if (charSearch == workName) {
                    //if (dataFilterList != resultList)
                    resultList = ArrayList()
                    for (row in data) {
                        when (location) {
                            "" -> {
                                if (row.workName.toLowerCase(Locale.ROOT).contains(workName.toLowerCase(Locale.ROOT))) {
                                    resultList.add(row)
                                }
                            }
                            else -> {
                                if (row.workName.toLowerCase(Locale.ROOT).contains(workName.toLowerCase(Locale.ROOT)) &&
                                        row.location.toLowerCase(Locale.ROOT).contains(location.toLowerCase(Locale.ROOT))) {
                                    resultList.add(row)
                                }
                            }
                        }
                    }
                    dataFilterList = resultList
                } else {
                    val resultList = ArrayList<Advert>()
                    for (row in data) {
                        when (workName) {
                            "" -> {
                                if (row.location.toLowerCase(Locale.ROOT).contains(location.toLowerCase(Locale.ROOT))) {
                                    resultList.add(row)
                                }
                            }
                            else -> {
                                if (row.workName.toLowerCase(Locale.ROOT).contains(workName.toLowerCase(Locale.ROOT)) &&
                                        row.location.toLowerCase(Locale.ROOT).contains(location.toLowerCase(Locale.ROOT))) {
                                    resultList.add(row)
                                }
                            }
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                //todo fix
                if (results?.values != null)
                    dataFilterList = results.values as ArrayList<Advert>
                notifyDataSetChanged()
            }

        }
    }
}