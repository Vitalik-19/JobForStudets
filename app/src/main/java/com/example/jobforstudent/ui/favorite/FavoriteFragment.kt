package com.example.jobforstudent.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobforstudent.CompanyData
import com.example.jobforstudent.R
import com.example.jobforstudent.databinding.FavoriteFragmentBinding

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FavoriteFragmentBinding =
                DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)
        binding.lifecycleOwner = this
        binding.apply {
            favoriteFragmentRecyclerView.adapter = FavoriteAdapter(CompanyData())
            favoriteFragmentRecyclerView.layoutManager = LinearLayoutManager(Fragment().context)
        }
        return binding.root
    }
}
