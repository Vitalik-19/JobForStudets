package com.example.jobforstudent.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobforstudent.CompanyData
import com.example.jobforstudent.R
import com.example.jobforstudent.databinding.FavoriteFragmentBinding

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: FavoriteFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        binding.favoriteViewModel = viewModel
        binding.setLifecycleOwner(this)
        binding.apply {
            favoriteFragmentRecyclerView.adapter = FavoriteAdapter(CompanyData())
            favoriteFragmentRecyclerView.layoutManager = LinearLayoutManager(Fragment().context)
        }
        return binding.root
    }
}
