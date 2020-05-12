package com.example.jobforstudent.ui.editadvert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.jobforstudent.R
import com.example.jobforstudent.databinding.EditAdvertFragmentBinding


class EditAdvertFragment : Fragment() {

    private lateinit var viewModel: EditAdvertViewModel
    private lateinit var binding: EditAdvertFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_advert_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(EditAdvertViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }
}
