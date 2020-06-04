package com.example.jobforstudent.ui.editadvert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.EditAdvertFragmentBinding


class EditAdvertFragment : Fragment() {

    private lateinit var binding: EditAdvertFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_advert_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = EditAdvertViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditAdvertViewModel::class.java)

        binding.editAdvertViewModel = viewModel
        if (arguments?.getLong("advertId") != null)
            viewModel.editTextFilling(arguments!!.getLong("advertId"))
        viewModel.addEvent.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
        binding.editAdvertFragmentAddButton.setOnClickListener {
            when (arguments?.getLong("advertId")) {
                null -> {
                    viewModel.onCreateAdvert()
                    it.findNavController().navigate(R.id.action_editAdvertFragment_to_advertEmployerFragment)
                }
                else -> {
                    viewModel.onUpdateAdvert()
                    it.findNavController().navigate(R.id.action_editAdvertFragment_to_advertEmployerFragment)
                }
            }
        }
        binding.lifecycleOwner = this
        return binding.root
    }
}
