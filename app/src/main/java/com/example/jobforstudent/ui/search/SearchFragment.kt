package com.example.jobforstudent.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
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
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = SearchViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        adapter = SearchAdapter()

        binding.apply {
            searchFragmentRecyclerView.adapter = adapter
            searchFragmentRecyclerView.layoutManager = LinearLayoutManager(Fragment().context)
            searchViewModel = viewModel
            lifecycleOwner = this@SearchFragment

            fragmentSearchSearchViewName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(s: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String?): Boolean {
                    if (s != "") {
                        val stringArray = s!!.split(" ")
                        for (string in stringArray) {
                            if (string == "") continue
                            adapter.workName = string
//                            Toast.makeText(context, string, Toast.LENGTH_LONG).show()
                            adapter.filter.filter(string)
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        adapter.workName = s ?: ""
                        adapter.filter.filter(s)
                        adapter.notifyDataSetChanged()
                    }
                    return false
                }

            })
            fragmentSearchSearchViewLocation.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(s: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String?): Boolean {
                    adapter.location = s ?: ""
                    adapter.filter.filter(s)
                    adapter.notifyDataSetChanged()
                    return false
                }

            })

        }

        viewModel.apply {
            navigateToAdvert.observe(viewLifecycleOwner, Observer {
                it?.let {

                }
            })
            adverts.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.data.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            })
            advertId.value = adapter.advertId
        }

        return binding.root
    }
}
