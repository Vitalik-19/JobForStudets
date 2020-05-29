package com.example.jobforstudent.ui.advertemployer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.AdvertEmployerFragmentBinding

class AdvertEmployerFragment : Fragment() {

    companion object {
        fun newInstance() = AdvertEmployerFragment()
    }

    private lateinit var viewModel: AdvertEmployerViewModel
    private lateinit var binding: AdvertEmployerFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.advert_employer_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = AdvertEmployerViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AdvertEmployerViewModel::class.java)
        val adapter = AdvertEmployerAdapter()

        binding.apply {
            advertEmployerFragmentRecyclerView.adapter = adapter
            advertEmployerFragmentRecyclerView.layoutManager = LinearLayoutManager(Fragment().context)
            advertEmployerViewModel = viewModel
            lifecycleOwner = this@AdvertEmployerFragment
            floatingActionButton.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_advertEmployerFragment_to_editAdvertFragment)
            )
        }

        viewModel.createAdvert.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })


        return binding.root
    }
}
