package com.example.jobforstudent.ui.editadvert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AdvertDatabase
import com.example.jobforstudent.databinding.EditAdvertFragmentBinding


class EditAdvertFragment : Fragment() {

    private lateinit var binding: EditAdvertFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_advert_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AdvertDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = EditAdvertViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditAdvertViewModel::class.java)

        binding.editAdvertViewModel = viewModel
        binding.editAdvertFragmentAddButton.setOnClickListener { view ->
            viewModel.dataFilling(
                    binding.editAdvertFragmentNameWorkEditText.text.toString(),
                    binding.editAdvertFragmentCompanyEditText.text.toString(),
                    binding.editAdvertFragmentLocationEditText.text.toString(),
                    binding.editAdvertFragmentSalaryEditText.text.toString().toInt()
            )
            viewModel.onCreateAdvert()
            view.findNavController().navigate(R.id.action_editAdvertFragment_to_navigation_search)
        }
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
