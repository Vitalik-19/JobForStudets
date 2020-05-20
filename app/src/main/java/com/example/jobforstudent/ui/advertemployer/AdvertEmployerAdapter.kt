package com.example.jobforstudent.ui.advertemployer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobforstudent.R
import com.example.jobforstudent.database.Employer
import com.example.jobforstudent.database.EmployerWithAdverts

class AdvertEmployerAdapter : RecyclerView.Adapter<AdvertEmployerAdapter.AdvertEmployerViewHolder>() {

    var data = listOf<Employer>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //    var adverts = data[0].advertList
    private val bundle = Bundle()

    init {
//    data[0].employer.employerId = 0L
    }

    class AdvertEmployerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameWorkText: TextView = itemView.findViewById(R.id.item_advert_owners_work_name_text)
        var companyNameText: TextView = itemView.findViewById(R.id.item_advert_owners_company_name_text)
        var locationText: TextView = itemView.findViewById(R.id.item_advert_owners_location_text)
        var salary: TextView = itemView.findViewById(R.id.item_advert_owners_salary_text)
        val favoriteImage: ImageView = itemView.findViewById(R.id.item_advert_owners_favorite_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertEmployerViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_advert_owners, parent, false)
        return AdvertEmployerAdapter.AdvertEmployerViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AdvertEmployerViewHolder, position: Int) {
        holder.apply {
            //todo output data
            nameWorkText.text = data[position].loginEmployer
            companyNameText.text = data[position].password
//            locationText.text = data[position].location
//            salary.text = data[position].salary.toString()
            favoriteImage.setOnClickListener {
                favoriteImage.setImageResource(R.drawable.ic_favorite_true_black_24dp)
            }
            itemView.setOnClickListener {
//                bundle.putLong("advertId", data[position].advertId)
//                it.findNavController().navigate(R.id.action_searchFragment_to_workFragment, bundle)
            }
        }
    }

}