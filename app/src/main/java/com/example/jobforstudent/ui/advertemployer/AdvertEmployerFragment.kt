package com.example.jobforstudent.ui.advertemployer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.jobforstudent.R
import com.example.jobforstudent.databinding.AdvertEmployerFragmentBinding

class AdvertEmployerFragment : Fragment() {

    companion object {
        fun newInstance() = AdvertEmployerFragment()
    }

    private lateinit var viewModel: AdvertEmployerViewModel
    private lateinit var binding: AdvertEmployerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.advert_employer_fragment, container, false)
        viewModel = ViewModelProvider(this).get(AdvertEmployerViewModel::class.java)
        binding.advertEmployerViewModel = viewModel
        binding.lifecycleOwner = this

        binding.floatingActionButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_advertEmployerFragment_to_editAdvertFragment)
        )

        return binding.root
    }
}
