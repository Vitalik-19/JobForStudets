package com.example.jobforstudent.ui.search

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
import com.example.jobforstudent.databinding.SearchFragmentBinding


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.searchViewModel = viewModel

        binding.lifecycleOwner = this
        binding.apply {
            searchFragmentRecyclerView.adapter = SearchAdapter(CompanyData())
            searchFragmentRecyclerView.layoutManager = LinearLayoutManager(Fragment().context)
        }
        return binding.root
    }
}
