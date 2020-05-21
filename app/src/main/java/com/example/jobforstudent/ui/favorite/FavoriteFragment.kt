package com.example.jobforstudent.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.FavoriteFragmentBinding

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: FavoriteFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = FavoriteViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteViewModel::class.java)
        val adapter = FavoriteAdapter()

        binding.favoriteViewModel = viewModel
        binding.lifecycleOwner = this

        binding.apply {
            favoriteFragmentRecyclerView.adapter = adapter
            favoriteFragmentRecyclerView.layoutManager = LinearLayoutManager(Fragment().context)
        }
        return binding.root
    }
}
