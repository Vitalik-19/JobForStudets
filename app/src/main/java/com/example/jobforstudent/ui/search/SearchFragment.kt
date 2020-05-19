package com.example.jobforstudent.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.SearchFragmentBinding


class SearchFragment : Fragment() {
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = SearchViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        val adapter = SearchAdapter()

        binding.searchFragmentRecyclerView.adapter = adapter
        binding.searchFragmentRecyclerView.layoutManager = LinearLayoutManager(Fragment().context)
        binding.searchViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigateToAdvert.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
        viewModel.adverts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        binding.button.setOnClickListener {
            //TODO search
        }
        return binding.root
    }
}
